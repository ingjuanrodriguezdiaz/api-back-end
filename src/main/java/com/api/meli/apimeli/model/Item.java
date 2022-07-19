package com.api.meli.apimeli.model;

public class Item {

    private String item_id;
    private Double precio;

    public Item() {
        
    }

    public Item(String item_id, Double precio) {
        this.item_id = item_id;
        this.precio = precio;
        
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}
