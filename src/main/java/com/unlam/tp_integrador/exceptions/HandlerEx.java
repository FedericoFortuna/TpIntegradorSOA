package com.unlam.tp_integrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerEx {

    private final String TAREA_NOT_FOUND_MSG = "No se encontro una tarea con ese id";
    private final String UNEXPECTED_MSG = "Error inesperado en el sistema";

    @ExceptionHandler(TareaNotFoundException.class)
    public ResponseEntity<String> tareaNotFoundEx(TareaNotFoundException ex) {
        // Aquí puedes realizar cualquier lógica personalizada para manejar la excepción
        // Por ejemplo, puedes registrarla en un archivo de log o realizar alguna operación específica

        return new ResponseEntity<>(TAREA_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<String> unexpectedEx(UnexpectedException ex) {
        // Aquí puedes realizar cualquier lógica personalizada para manejar la excepción
        // Por ejemplo, puedes registrarla en un archivo de log o realizar alguna operación específica

        return new ResponseEntity<>(UNEXPECTED_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
