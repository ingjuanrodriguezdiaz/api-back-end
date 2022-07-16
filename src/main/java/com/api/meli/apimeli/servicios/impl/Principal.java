package com.api.meli.apimeli.servicios.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.meli.apimeli.servicios.IPrincipal;

@Service("servicio_principal")
public class Principal implements IPrincipal {

    @Override
    public Map<String, Double> listar() {
        Map<String, Double> mapa= new HashMap<>();
        mapa.put("camilo", new Double("200"));
        return mapa;
    }
    
}
