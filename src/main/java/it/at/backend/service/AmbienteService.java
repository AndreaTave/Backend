package it.at.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.at.backend.entity.Ambiente;
import it.at.backend.repository.AmbienteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AmbienteService {

    @Autowired
    AmbienteRepository repo;

    public Ambiente findByTabellaValore(String t, String v) {
        log.info("LOG findByTabellaValore()");

        return this.repo.findByTabellaAndValore(t, v);
    }

}
