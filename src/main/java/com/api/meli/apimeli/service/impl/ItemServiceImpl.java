package com.api.meli.apimeli.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.api.meli.apimeli.service.IItemService;
import com.api.meli.apimeli.exceptions.ItemException;
import com.api.meli.apimeli.model.ItemsMount;

@Service
@Repository
@Transactional
public class ItemServiceImpl implements IItemService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ItemServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Override
    public Map<String, Float> items() {
        Map<String, Float> mapa = new HashMap<>();
        mapa.put("MLA1", new Float(100));
        mapa.put("MLA2", new Float(210));
        mapa.put("MLA3", new Float(260));
        mapa.put("MLA4", new Float(80));
        mapa.put("MLA5", new Float(90));
        return mapa;
    }

    @Override
    public Map<String, Float> consultarItems() {
        Map<String, Float> mapa = new HashMap<>();
        Map<String, Double> res = new HashMap<>();
        String urlItems = env.getProperty("app.service.items.url", String.class);
        try {
            logger.info("res:" + urlItems);
            res = restTemplate.getForObject(urlItems + "items", Map.class);
            for (Map.Entry<String, Double> element : res.entrySet()) {
                mapa.put(element.getKey(), element.getValue().floatValue());
            }

        } catch (Exception e) {
            String error = "Error al consumir servicio consultar Items";
            logger.error(error + e.getMessage());
            // throw new NegocioException(error);
        }
        return mapa;
    }

    @Override
    public List<String> calculate() throws ItemException {
        Map<String, Float> items = consultarItems();
        List<String> lista = new ArrayList<>();
        descartar(items);
        lista = this.calculate(items, new Float(500));
 
        return lista;
    }

    private Map.Entry<String, Float> buscarElement(Map<String, Float> items,Float amount){
        for (Map.Entry<String, Float> element : items.entrySet()) {
            if(element.getValue().floatValue() == amount.floatValue()){
                return element;
            }
        }

        return null;
    }

    @Override
    public List<String> calculate(Map<String, Float> items, Float amount) throws ItemException {

        Float res = sumar(items).floatValue();
        Map<String, Float> keyValues = new HashMap<String, Float>();
        Map<String, Float> keyValuesResAsc = new HashMap<>();
        Map<String, Float> keyValuesResDesc = new HashMap<>();    

        // Map.Entry<String, Float> eleBuscar= buscarElement(items, amount);
        // if(eleBuscar != null){
        //     List<String> resList =new ArrayList<String>();
        //     resList.add(eleBuscar.getKey());
        //     return resList;
        // }
        if (amount.floatValue() <= res.floatValue()) {
            res = amount ;
            List<Entry<String, Float>> list = new ArrayList<>(items.entrySet());
            list.sort(Entry.comparingByValue(Comparator.naturalOrder()));    
            Float saldo = new Float(amount);
            Float suma = new Float(0);   
            Float totalAsc = new Float(0);  
            Float totalDesc = new Float(0);    
            for (Entry<String, Float> element : list) {
                suma+= element.getValue().floatValue();
                if(element.getValue().floatValue() <= res.floatValue()){                   
                    keyValues.put(element.getKey(), element.getValue());
                  
                }
            } 
            List<Entry<String, Float>> listResAsc = new ArrayList<>(keyValues.entrySet());
            List<Entry<String, Float>> listResDesc = new ArrayList<>(keyValues.entrySet());
            listResAsc.sort(Entry.comparingByValue(Comparator.naturalOrder()));    
            listResDesc.sort(Entry.comparingByValue(Comparator.reverseOrder()));    
            for (Entry<String, Float> element : listResAsc) {
                if (saldo.doubleValue() > 0.0 && saldo.doubleValue() >= element.getValue().floatValue()) {
                    totalAsc+=element.getValue().floatValue();
                    keyValuesResAsc.put(element.getKey(), element.getValue());
                    saldo = saldo.floatValue() - element.getValue().floatValue();
                }              
             
            }  
            saldo = new Float(amount); 

            for (Entry<String, Float> element : listResDesc) {
                if (saldo.doubleValue() > 0.0 && saldo.doubleValue() >= element.getValue().floatValue()) {
                    totalDesc+=element.getValue().floatValue();
                    keyValuesResDesc.put(element.getKey(), element.getValue());
                    saldo = saldo.floatValue() - element.getValue().floatValue();
                }        
            }  
            if(totalAsc.floatValue() < totalDesc.floatValue()){
                return new ArrayList<String>(keyValuesResDesc.keySet());
            }else{
                return new ArrayList<String>(keyValuesResAsc.keySet());
            }

        }

        return new ArrayList<String>(items.keySet());
    }

    public Float sumar(Map<String, Float> items) {
        Float sumatoria = new Float(0);
        for (Map.Entry<String, Float> element : items.entrySet()) {
            sumatoria += element.getValue().floatValue();
        }
        return sumatoria;
    }

    private void descartar(List<String> items) throws ItemException {
        Map<String, Integer> res = new HashMap<>();
        for (String key : items) {
            if (res.containsKey(key)) {
                throw new ItemException(1, "Solo se puede comprar una unidad por item");
            } else {
                res.put(key, 1);
            }
        }
    }

    private void descartar(Map<String, Float> items) throws ItemException {
        Map<String, Integer> res = new HashMap<>();

        for (Map.Entry<String, Float> element : items.entrySet()) {
            if (res.containsKey(element.getKey())) {
                throw new ItemException(1, "Solo se puede comprar una unidad por item");
            } else {
                res.put(element.getKey(), 1);
            }
        }
    }

    @Override
    public ResponseEntity<?> coupon(ItemsMount itemsMount) throws ItemException {

            descartar(itemsMount.getItem_ids());
            Map<String, Float> listar = consultarItems();    
            ItemsMount response = new ItemsMount();
            Map<String, Float> nuevo = new HashMap<String, Float>();
            for (String item : itemsMount.getItem_ids()) {
                nuevo.put(item, listar.get(item).floatValue());
            }
            List<String> sumar = calculate(nuevo, itemsMount.getAmount().floatValue());

            for (String key : sumar) {
                Float valor = listar.get(key);
                response.setTotal(response.getTotal().floatValue() + valor.floatValue());
            }
            response.setItem_ids(sumar);

            if (!sumar.isEmpty() && sumar.size() > 0) {
                return new ResponseEntity<ItemsMount>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>("Monto no es suficiente para comprar un producto", HttpStatus.NOT_FOUND);     

    } 

}
