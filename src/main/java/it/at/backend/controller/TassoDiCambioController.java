package it.at.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import it.at.backend.dto.Errore;
import it.at.backend.dto.Risposta;
import it.at.backend.service.BancaDItaliaService;
import it.at.backend.utils.Libreria;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tdc")
@CrossOrigin("*")
@Slf4j
public class TassoDiCambioController {
    
    @Autowired
    BancaDItaliaService biService;

    @GetMapping(value="/valute", produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Risposta> valute(/*@RequestBody body */) {
        Errore errore=new Errore();
        Risposta risposta=new Risposta();

        log.info("TDC - /valute");

        try {
            String responseBody=this.biService.elencoValute();
            risposta.setDati(responseBody);
            risposta.setEsito("OK");
        }
        catch(Exception e) {
            errore=Libreria.gestisciEccezioni(e, "");
            risposta.setErrore(errore);
            risposta.setEsito("KO");
        }

        HttpHeaders headers=new HttpHeaders();

        return new ResponseEntity<Risposta>(risposta, headers, HttpStatus.OK);
    }


}
