package com.unlam.tp_integrador.exceptions;

import org.webjars.NotFoundException;

public class ProductoNotFoundException extends NotFoundException {

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
