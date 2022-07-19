package com.api.meli.apimeli.exceptions;


public class ItemException extends Exception {
    public static final long serialVersionUID = 700L;

    private int option;   

    public ItemException(int opcion,String error){
        super(error);
        this.option = opcion;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
    
}