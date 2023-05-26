package com.unlam.tp_integrador.threads;

import com.unlam.tp_integrador.communication.Message;
import com.unlam.tp_integrador.communication.MessageProducer;
import com.unlam.tp_integrador.communication.MessageQueue;
import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import com.unlam.tp_integrador.exceptions.TareaNotFoundException;
import com.unlam.tp_integrador.mapper.MapperTarea;
import com.unlam.tp_integrador.processor.ProcesadorTarea;
import com.unlam.tp_integrador.repositories.TareaRepository;
import com.unlam.tp_integrador.strategy.process.CalculationStrategy;
import com.unlam.tp_integrador.strategy.process.TextTransformStrategy;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;


public class ExecutorThread extends Thread {

    private TareaDTO tareaDTO;
    private ProcesadorTarea procesadorTarea;
    private TareaRepository tareaRepository;

    @Autowired
    private MessageProducer mp;


    public ExecutorThread(TareaDTO tareaDTO, ProcesadorTarea procesadorTarea, TareaRepository tareaRepository) {
        this.tareaDTO = tareaDTO;
        this.procesadorTarea = procesadorTarea;
        this.tareaRepository = tareaRepository;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Optional<TareaEntity> tareaEntity = tareaRepository.findById(this.tareaDTO.getId());

        if (tareaEntity.isEmpty()) {
            throw new TareaNotFoundException();
        }

        TareaDTO tareaDTO = MapperTarea.toDTO(tareaEntity.get());
        TipoTarea tipoTarea = TipoTarea.valueOf(tareaDTO.getTipoTarea().toString());

        if (tipoTarea.equals(TipoTarea.CALCULATION)) {
            procesadorTarea.procesarTarea(tareaDTO, new CalculationStrategy());
        } else if (tipoTarea.equals(TipoTarea.TEXT_TRANSFORM)) {
            procesadorTarea.procesarTarea(tareaDTO, new TextTransformStrategy());
        }


        tareaDTO.setStatusTarea(StatusTarea.EN_PROGRESO);
        tareaRepository.save(MapperTarea.toEntity(tareaDTO));

        mp.sendMessage(Message.builder()
                .requestId(tareaDTO.getId())
                .resultado(tareaDTO.getResultado())
                .build());

    }
}
