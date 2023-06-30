package com.unlam.tp_integrador.controller;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.service.IProcesadorTareaService;
import com.unlam.tp_integrador.tools.LoggingTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ProcesadorTareaController {

    public final String POST_TASK_ENDPOINT = "{} - Recibido: {} - {}";
    public final String GET_TASK_ENDPOINT = "{} - Obteniendo info para id: {} - {} - {}";

    @Autowired
    private IProcesadorTareaService procesadorTareaService;


    @Operation(summary = "Publicar tarea",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error Inesperado")})
    @PostMapping(value = "/task")
    public ResponseEntity<String> postTask(@RequestBody TareaDTO tareaDTO){
        log.info(POST_TASK_ENDPOINT, LoggingTag.CONTROLLER, LocalDateTime.now(), ProcesadorTareaController.class.getSimpleName());
        tareaDTO = procesadorTareaService.crearTarea(tareaDTO);
        procesadorTareaService.procesarTareaAsync(tareaDTO);
        return new ResponseEntity<>(tareaDTO.getId(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener resultados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Error Inesperado")})
    @GetMapping(value = "/task/{idTask}")
    public ResponseEntity<String> getTask(@PathVariable String idTask){
        log.info(GET_TASK_ENDPOINT, LoggingTag.CONTROLLER, idTask, LocalDateTime.now(), ProcesadorTareaController.class.getSimpleName());
        return new ResponseEntity<>(procesadorTareaService.obtenerResultado(idTask), HttpStatus.OK);
    }


}
