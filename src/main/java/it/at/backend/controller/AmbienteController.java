package it.at.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.at.backend.service.AmbienteService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@CrossOrigin("*")
@Slf4j
public class AmbienteController {

    @Autowired
    AmbienteService service;
}
