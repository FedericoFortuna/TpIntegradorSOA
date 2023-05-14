package com.unlam.tpIntegrador.administrator;

import com.unlam.tpIntegrador.entities.Producto;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Component
@Getter
public class ManejadorStock {
    private Map<Producto, Integer> stockProductos = new HashMap() {{
        put(Producto.builder().id("1").desc("Yerba").precio(800).build(), 10);
        put(Producto.builder().id("2").desc("Azucar").precio(250).build(), 25);
        put(Producto.builder().id("3").desc("Sal").precio(175).build(), 200);
        put(Producto.builder().id("4").desc("Lavandina").precio(350).build(), 15);
        put(Producto.builder().id("5").desc("Papel de cocina").precio(600).build(), 48);
        put(Producto.builder().id("6").desc("Oregano").precio(20).build(), 300);
    }};

    public void saveProducto(Producto producto, Integer cantidad) {
        boolean existe = false;
        int c = 0;
        Producto productoActualizado = new Producto();
        Producto productoAnterior = new Producto();
        for (Producto p : stockProductos.keySet()) {
            if (Objects.equals(producto.getId(), p.getId())) {
                productoAnterior = p;
                productoActualizado = p.actualizarProducto(producto);
                c = stockProductos.get(p);
                existe = true;
            }
        }
        if (!existe) {
            stockProductos.put(producto, cantidad);
        } else {
            stockProductos.remove(productoAnterior);
            stockProductos.put(productoActualizado, c + cantidad);
        }
    }

    public void eliminarProducto(String idProducto, Integer cantidad) {
        boolean existe = false;
        int cantidadProducto = 0;
        Producto producto = new Producto();
        for (Producto p : stockProductos.keySet()) {
            if (Objects.equals(idProducto, p.getId())) {
                cantidadProducto = stockProductos.get(p);
                producto = p;
                existe = true;
            }
        }
        if(existe && cantidad < cantidadProducto){
            stockProductos.replace(producto, cantidadProducto, cantidadProducto - cantidad);
        } else if (existe && cantidad == cantidadProducto){
            stockProductos.remove(producto);
        } else {
            //exception
        }
    }
}
