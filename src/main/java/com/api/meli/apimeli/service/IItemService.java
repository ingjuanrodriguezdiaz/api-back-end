package com.api.meli.apimeli.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.meli.apimeli.exceptions.ItemException;
import com.api.meli.apimeli.model.ItemsMount;
@Service
public interface IItemService {

    Map<String, Float> items();
    Map<String, Float> consultarItems();
    List<String> calculate() throws ItemException;
    List<String> calculate(Map<String, Float> items, Float amount)  throws ItemException ;
    ResponseEntity<?> coupon(ItemsMount itemsMount) throws ItemException;
}
