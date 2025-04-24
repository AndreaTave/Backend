package it.at.backend.service;

import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import it.at.backend.utils.CustomException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class BancaDItaliaService {
private final WebClient httpClient;
    
    public String elencoValute() throws URISyntaxException {

        ClientResponse response=this.httpClient.method(HttpMethod.GET)
        .uri("https://tassidicambio.bancaditalia.it/terzevalute-wf-web/rest/v1.0/currencies")
        .accept(MediaType.ALL)
        .contentType(MediaType.APPLICATION_JSON)
        .exchange()
        .block();

        log.info("RESPONSE: "+response.toString());

        if(response.statusCode().is2xxSuccessful()) {
           return response.bodyToMono(String.class).block();
        }
        else {
            throw new CustomException(response.statusCode().value(), "Errore chiamata valute");
        }

    }
}
