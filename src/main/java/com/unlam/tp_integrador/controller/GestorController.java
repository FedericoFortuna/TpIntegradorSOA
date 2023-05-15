package com.unlam.tp_integrador.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.unlam.tp_integrador.entities.Producto;
import com.unlam.tp_integrador.request.ProductoRequest;
import com.unlam.tp_integrador.service.GestorProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GestorController {

    @Autowired
    private GestorProductoService gestorProductoService;

    @Operation(summary = "Get Products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Producto.class))),
                    @ApiResponse(responseCode = "500", description = "Error Ineserado")})
    @GetMapping(value = "/getProductos")
    public ResponseEntity<String> getProducts() throws JsonProcessingException {
        return new ResponseEntity<>(gestorProductoService.getProducts(), HttpStatus.OK);
    }


    @Operation(summary = "Save Products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error Ineserado")})
    @PostMapping(value = "/saveProducto")
    public ResponseEntity<String> updateProduct(@RequestBody ProductoRequest productoRequest){
        gestorProductoService.saveProduct(productoRequest.getProducto(), productoRequest.getCantidad());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Operation(summary = "Delete Products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Product not Found"),
                    @ApiResponse(responseCode = "500", description = "Error Ineserado")})
    @PutMapping(value = "/deleteProducto/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id, @RequestBody Integer cantidad){
        gestorProductoService.deleteProduct(id, cantidad);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


}
