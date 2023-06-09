package com.unlam.tp_integrador.threads;

import com.unlam.tp_integrador.communication.Message;
import com.unlam.tp_integrador.communication.MessageProducer;
import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import com.unlam.tp_integrador.exceptions.TareaNotFoundException;
import com.unlam.tp_integrador.exceptions.UnexpectedException;
import com.unlam.tp_integrador.mapper.MapperTarea;
import com.unlam.tp_integrador.processor.ProcesadorTarea;
import com.unlam.tp_integrador.repositories.TareaRepository;
import com.unlam.tp_integrador.strategy.process.*;
import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class ExecutorThread extends Thread {

    private final String EXECUTION_THREAD = "{} - Inicio ejecucion hilo: {} - {} - {}";
    private final String SEND_MESSAGE = "{} - Hilo: {} enviando mensaje - {} - {}";
    private final String LOOK_FOR_TASK = "{} - Buscando tarea con id: {} - {} - {}";
    private final String TASK_FOUND = "{} - Tarea encontrada con id: {} - {} - {}";
    private final String INSERT_TASK_IN_PROGRESS = "{} - Guardando tarea con id: {} EN PROGRESO - {} - {}";
    private final String TASK_NOT_FOUND = "{} - Tarea no encontrada - {} - {}";
    private final String ERROR = "{} - Error en sleep thread - {} - {}";
    private final String TASK = "{} - Hilo: {} - para tarea de tipo {} - {} - {}";

    private final TareaDTO tareaDTO;
    private final ProcesadorTarea procesadorTarea;
    private final TareaRepository tareaRepository;

    @Autowired
    private final MessageProducer mp;


    public ExecutorThread(TareaDTO tareaDTO, ProcesadorTarea procesadorTarea, TareaRepository tareaRepository) {
        this.tareaDTO = tareaDTO;
        this.procesadorTarea = procesadorTarea;
        this.tareaRepository = tareaRepository;
        this.mp = new MessageProducer();
    }


    @Override
    public void run() {
        try {
            log.info(EXECUTION_THREAD, LoggingTag.THREAD, Thread.currentThread().getName(), LocalDateTime.now().withNano(0), ExecutorThread.class.getSimpleName());
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            log.error(ERROR, LoggingTag.THREAD, Thread.currentThread().getName(), LocalDateTime.now().withNano(0));
            throw new UnexpectedException();
        }

        log.info(LOOK_FOR_TASK, LoggingTag.THREAD, this.tareaDTO.getId(), LocalDateTime.now().withNano(0), Thread.currentThread().getName());
        Optional<TareaEntity> tareaEntity = tareaRepository.findById(this.tareaDTO.getId());

        if (tareaEntity.isEmpty()) {
            log.info(TASK_NOT_FOUND, LoggingTag.THREAD, LocalDateTime.now().withNano(0), ExecutorThread.class.getSimpleName());
            throw new TareaNotFoundException();
        }

        log.info(TASK_FOUND, LoggingTag.THREAD, this.tareaDTO.getId(), LocalDateTime.now().withNano(0), Thread.currentThread().getName());

        TareaDTO tareaFounded = MapperTarea.toDTO(tareaEntity.get());
        TipoTarea tipoTarea = TipoTarea.valueOf(tareaFounded.getTipoTarea().toString());

        process(tareaFounded, tipoTarea);

        saveNewStatus();

        sendMessage(tareaFounded);

    }


    private void process(TareaDTO tareaDTO, TipoTarea tipoTarea) {
       if (tipoTarea.equals(TipoTarea.TEXT_TRANSFORM)) {
            log.info(TASK, LoggingTag.THREAD, Thread.currentThread().getName(), tipoTarea, LocalDateTime.now().withNano(0), ExecutorThread.class.getSimpleName());
            procesadorTarea.procesarTarea(tareaDTO, new TextTransformStrategy());
        } else if (tipoTarea.equals(TipoTarea.HASH_PW)) {
            log.info(TASK, LoggingTag.THREAD, Thread.currentThread().getName(), tipoTarea, LocalDateTime.now().withNano(0), ExecutorThread.class.getSimpleName());
            procesadorTarea.procesarTarea(tareaDTO, new HasherStrategy());
        } else if (tipoTarea.equals(TipoTarea.BRAILE)) {
            log.info(TASK, LoggingTag.THREAD, Thread.currentThread().getName(), tipoTarea, LocalDateTime.now().withNano(0), ExecutorThread.class.getSimpleName());
            procesadorTarea.procesarTarea(tareaDTO, new BraileTransformStrategy());
        } else if (tipoTarea.equals(TipoTarea.CUIL)) {
            log.info(TASK, LoggingTag.THREAD, Thread.currentThread().getName(), tipoTarea, LocalDateTime.now().withNano(0), ExecutorThread.class.getSimpleName());
            procesadorTarea.procesarTarea(tareaDTO, new CuilStrategy());
        }
    }

    private void sendMessage(TareaDTO tareaDTO) {
        log.info(SEND_MESSAGE, LoggingTag.THREAD, Thread.currentThread().getName(), LocalDateTime.now().withNano(0), ExecutorThread.class.getSimpleName());
        mp.sendMessage(Message.builder()
                .requestId(tareaDTO.getId())
                .resultado(tareaDTO.getResultado())
                .build());
    }

    private void saveNewStatus() {
        tareaDTO.setStatusTarea(StatusTarea.EN_PROGRESO);
        log.info(INSERT_TASK_IN_PROGRESS, LoggingTag.THREAD, this.tareaDTO.getId(), LocalDateTime.now().withNano(0), Thread.currentThread().getName());
        tareaRepository.save(MapperTarea.toEntity(tareaDTO));
    }

}
