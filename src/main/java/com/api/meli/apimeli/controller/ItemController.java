package com.api.meli.apimeli.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.meli.apimeli.exceptions.ItemException;
import com.api.meli.apimeli.model.ItemsMount;
import com.api.meli.apimeli.service.IItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
    
    @Autowired
    IItemService service;

    @CrossOrigin
	@GetMapping(value="/items",produces = "application/json")
	 public Map<String, Float> items() {	    
		  return service.items();
	}

    @CrossOrigin
	@GetMapping(value="/consultar",produces = "application/json")
	 public Map<String, Float> consultarItems() {	    
		  return service.consultarItems();
	}  

    @CrossOrigin
	@PostMapping(value="/coupon",produces = "application/json")
    public ResponseEntity<?> coupon(@RequestBody ItemsMount itemsMount) throws ItemException {     
        return service.coupon(itemsMount);             
    }

    @CrossOrigin
	@GetMapping(value="/nivel1",produces = "application/json")
	 public ResponseEntity<?> nivel1() throws ItemException {	    
		  return ResponseEntity.ok(service.calculate());
	}
}
