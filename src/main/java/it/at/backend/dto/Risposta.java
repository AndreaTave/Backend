package it.at.backend.dto;

import lombok.Data;

@Data
public class Risposta {
    private String esito;
    private String dati;
    private Errore errore;
}
