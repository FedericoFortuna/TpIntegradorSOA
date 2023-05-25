package com.unlam.tp_integrador.exceptions;

import org.webjars.NotFoundException;

public class TareaNotFoundException extends NotFoundException {

    public TareaNotFoundException(){
        super("");
    }

    public TareaNotFoundException(String message) {
        super(message);
    }
}
