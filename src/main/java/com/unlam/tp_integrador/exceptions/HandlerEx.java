package com.unlam.tp_integrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class HandlerEx {

    private final String PRODUCTO_NOT_FOUND = "Producto inexistente";
    private final String SIN_STOCK = "El producto no tiene stock suficiente. Verificar Stock";


    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<Object> handleProductoNotFoundException(
            ProductoNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", PRODUCTO_NOT_FOUND);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SinStockException.class)
    public ResponseEntity<Object> handleSinStockException(
            SinStockException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", SIN_STOCK);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


}
