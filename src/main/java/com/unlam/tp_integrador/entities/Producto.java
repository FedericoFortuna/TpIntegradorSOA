package com.unlam.tp_integrador.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("precio")
    private int precio;

    public Producto actualizarProducto(Producto producto){
        return Producto.builder()
                .id(producto.getId())
                .desc(producto.getDesc())
                .precio(producto.getPrecio())
                .build();
    }

}
