package com.unlam.tpIntegrador.request;

import com.unlam.tpIntegrador.entities.Producto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {
    private Producto producto;
    private int cantidad;
}
