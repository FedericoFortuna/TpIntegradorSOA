package com.unlam.tp_integrador.controller;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.service.IProcesadorTareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProcesadorTareaController {

    @Autowired
    private IProcesadorTareaService procesadorTareaService;


    @Operation(summary = "Publicar tarea",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error Ineserado")})
    @PostMapping(value = "/task")
    public ResponseEntity<String> postTask(@RequestBody TareaDTO tareaDTO){
        tareaDTO = procesadorTareaService.crearTarea(tareaDTO);
        procesadorTareaService.procesarTareaAsync(tareaDTO);
        return new ResponseEntity<>(tareaDTO.getId(), HttpStatus.OK);
    }


}
