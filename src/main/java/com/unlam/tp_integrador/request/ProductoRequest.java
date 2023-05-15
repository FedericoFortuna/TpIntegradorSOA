package com.unlam.tp_integrador.request;

import com.unlam.tp_integrador.entities.Producto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {
    private Producto producto;
    private int cantidad;
}
