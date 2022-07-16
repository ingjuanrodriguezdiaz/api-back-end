package com.api.meli.apimeli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.meli.apimeli.servicios.IPrincipal;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class Principal {

    @Autowired
    @Qualifier("servicio_principal")
    private IPrincipal servicio;

    @GetMapping("/listar")
    public ResponseEntity <?> get() { 

        return new ResponseEntity<>(servicio.listar(),HttpStatus.OK); 
    }
}
