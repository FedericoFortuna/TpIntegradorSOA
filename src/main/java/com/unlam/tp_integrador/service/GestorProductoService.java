package com.unlam.tp_integrador.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlam.tp_integrador.administrator.ManejadorStock;
import com.unlam.tp_integrador.entities.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorProductoService {

    @Autowired
    private ManejadorStock manejadorStock;

    public String getProducts() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(manejadorStock.getStockProductos());
    }

    public void saveProduct(Producto producto, int cantidad) {
        manejadorStock.saveProducto(producto, cantidad);
    }

    public void deleteProduct(String id, int cantidad) {
        manejadorStock.eliminarProducto(id, cantidad);
    }

}
