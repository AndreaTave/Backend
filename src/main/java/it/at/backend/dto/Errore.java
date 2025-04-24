package it.at.backend.dto;

import lombok.Data;

@Data
public class Errore {
    private String codice;
    private String causa;
    private String descrizione;
    private String datiInErrore;
}
