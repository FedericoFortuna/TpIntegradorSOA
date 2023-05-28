package com.unlam.tp_integrador.service;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.mapper.MapperTarea;
import com.unlam.tp_integrador.processor.ProcesadorTarea;
import com.unlam.tp_integrador.repositories.TareaRepository;
import com.unlam.tp_integrador.threads.ExecutorThread;
import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
public class ProcesadorTareaServiceImpl implements IProcesadorTareaService {

    private final String CREATE_TASK = "{} - Creando tarea con id: {} - {} - {}";
    private final String THREAD_FOR_TASK = "{} - Creando hilo para tarea con id: {} - {} - {}";

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

}
