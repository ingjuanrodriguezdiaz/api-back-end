package com.api.meli.apimeli.model;

import java.util.List;

public class ItemsMount {
    private List<String> item_ids;
    private Float amount;
    private Float total;
    
    public ItemsMount() {
            this.total = new Float(0);
    }
    public ItemsMount(List<String> item_ids, Float amount) {
        this.item_ids = item_ids;
        this.amount = amount;
    }
    public List<String> getItem_ids() {
        return item_ids;
    }
    public void setItem_ids(List<String> item_ids) {
        this.item_ids = item_ids;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public Float getTotal() {
        return total;
    }
    public void setTotal(Float total) {
        this.total = total;
    }       

}
