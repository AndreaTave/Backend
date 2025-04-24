package it.at.backend.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.impl.DefaultClock;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import it.at.backend.utils.JwtProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class JWTUtils {
    private String secret;

    @Autowired
    public JWTUtils(JwtProperties p) {
        log.info("IL SECRET: "+p.getSecret());
        this.secret=p.getSecret();
    }

    public String generaToken(String utente) {
        Clock clock=DefaultClock.INSTANCE;
        final Date dataCreazione=clock.now(); //DATA CREAZIONE TOKEN
        final Date dataScadenza=new Date( dataCreazione.getTime()+(30*60*1000) ); //DATA SCADENZA TOKEN - 30MINUTI

        HashMap<String, Object> claims=new HashMap<String, Object>();
        claims.put("utente", utente);

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(claims)
                .subject(utente)
                .issuedAt(dataCreazione)
                .expiration(dataScadenza)
                .signWith(key)
                .compact();
    }

    public boolean validaToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        try {
            Jwts.parser().decryptWith(key).build().parse(token);
            return true;
        }
        catch(SecurityException  | MalformedJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT non corretto o scaduto.");
        }
        catch(ExpiredJwtException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT scaduto.");
        }
        catch(IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT handler non valido.");
        }
        
    }

    public String getFromJWT(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Claims claims=Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        
        if(claims.containsKey("utente")) {
            return claims.get("utente").toString();
        }
        else {
            throw new AuthenticationCredentialsNotFoundException("Utente non trovato nei claims del JWT");
        }
    }

    public HttpHeaders creaHeadersRisposta(String jwtBearer) {
        String jwt=jwtBearer.substring("Bearer".length()).trim(); //MI PRENDO LA PARTE UTILE DEL TOKEN
        String utenteLoggato=getFromJWT(jwt); //MI PRENDO L'UTENTE CHE C'E' SCRITTO DENTRO
        String tokenNuovo=generaToken(utenteLoggato); //CREO UN NUOVO TOKEN
        
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization", tokenNuovo);
        headers.add("Access-Control-Expose-Headers", "Authorization");

        return headers;
    }
}
