package com.unlam.tp_integrador.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.exceptions.TareaNotFoundException;
import com.unlam.tp_integrador.exceptions.UnexpectedException;
import com.unlam.tp_integrador.mapper.MapperTarea;
import com.unlam.tp_integrador.processor.ProcesadorTarea;
import com.unlam.tp_integrador.repositories.TareaRepository;
import com.unlam.tp_integrador.response.TareaResponse;
import com.unlam.tp_integrador.threads.ExecutorThread;
import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Slf4j
public class ProcesadorTareaServiceImpl implements IProcesadorTareaService {

    private final String CREATE_TASK = "{} - Creando tarea con id: {} - {} - {}";
    private final String THREAD_FOR_TASK = "{} - Creando hilo para tarea con id: {} - {} - {}";
    private final String LOOK_FOR_TASK = "{} - Buscando resultado de tarea con id: {} - {} - {}";
    private final String TASK_FOUND = "{} - Tarea encontrada con id: {} - {} - {}";
    private final String TASK_NOT_FOUND = "{} - Tarea con id {} no encontrada - {} - {}";


    private final ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProcesadorTarea procesadorTarea;

    @Override
    public TareaDTO crearTarea(TareaDTO tareaDTO) {
        String idTarea = procesadorTarea.generarRandomId();
        log.info(CREATE_TASK, LoggingTag.SERVICE_EXECUTION, idTarea, LocalDateTime.now().withNano(0), ProcesadorTareaServiceImpl.class.getSimpleName());
        tareaRepository.save(MapperTarea.newTarea(tareaDTO, idTarea));
        tareaDTO.setId(idTarea);
        return tareaDTO;
    }

    @Override
    public void procesarTareaAsync(TareaDTO tareaDTO) {
        ExecutorThread executorThread = new ExecutorThread(tareaDTO, procesadorTarea, tareaRepository);
        log.info(THREAD_FOR_TASK, LoggingTag.SERVICE_EXECUTION, tareaDTO.getId(), LocalDateTime.now().withNano(0), ProcesadorTareaServiceImpl.class.getSimpleName());
        executorThread.start();
    }

    @Override
    public String obtenerResultado(String idTask) {
        log.info(LOOK_FOR_TASK, LoggingTag.SERVICE_EXECUTION, idTask, LocalDateTime.now().withNano(0), ProcesadorTareaServiceImpl.class.getSimpleName());
        Optional<TareaEntity> tareaEntityOptional = tareaRepository.findById(idTask);

        if(tareaEntityOptional.isEmpty()){
            log.info(TASK_NOT_FOUND, LoggingTag.SERVICE_EXECUTION, idTask, LocalDateTime.now().withNano(0), ProcesadorTareaServiceImpl.class.getSimpleName());
            throw new TareaNotFoundException();
        }
        log.info(TASK_FOUND, LoggingTag.SERVICE_EXECUTION, idTask, LocalDateTime.now().withNano(0), ProcesadorTareaServiceImpl.class.getSimpleName());

        try {
            return objectMapper.writeValueAsString(MapperTarea.toResponse(tareaEntityOptional.get()));
        } catch (JsonProcessingException e) {
            throw new UnexpectedException();
        }

    }

}
