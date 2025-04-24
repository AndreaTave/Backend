package it.at.backend.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="ambiente")
@Data
@NoArgsConstructor
public class Ambiente {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="tabella")
    private String tabella;

    @Column(name="valore")
    private String valore;

    @Column(name="descrizione")
    private String descrizione;

    @Column(name="ts_inserimento", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private LocalDateTime tsInserimento;

    @Column(name="ts_modifica", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private LocalDateTime tsModifica;

    public Ambiente(String t, String v) {
        this.setTabella(t);
        this.setValore(v);
    }
}
