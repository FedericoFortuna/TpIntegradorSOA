package com.unlam.tp_integrador.service;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import com.unlam.tp_integrador.mapper.MapperTarea;
import com.unlam.tp_integrador.processor.ProcesadorTarea;
import com.unlam.tp_integrador.repositories.TareaRepository;
import com.unlam.tp_integrador.strategy.CalculationStrategy;
import com.unlam.tp_integrador.strategy.ReportGenerationStrategy;
import com.unlam.tp_integrador.strategy.TextTransformStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

@Service
public class ProcesadorTareaServiceImpl implements IProcesadorTareaService {

    private PipedInputStream pipedInputStream;
    private PipedOutputStream pipedOutputStream;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    ProcesadorTarea procesadorTarea;

    @Override
    public String crearTarea(TareaDTO tareaDTO) {
        String idTarea = procesadorTarea.generarRandomId();
        tareaRepository.save(MapperTarea.newTarea(tareaDTO, idTarea));
        return idTarea;
    }

    @Override
    public void procesarTareaAsync(TareaDTO tareaDTO) {
        Thread thread = new Thread(() -> procesarTarea(tareaDTO));

        thread.start();
    }

    private synchronized void procesarTarea(TareaDTO tareaDTO){

        TipoTarea tipoTarea = tareaDTO.getTipoTarea();

        if(tipoTarea.equals(TipoTarea.CALCULATION)){
            procesadorTarea.procesarTarea(tareaDTO, new CalculationStrategy());
        }else if(tipoTarea.equals(TipoTarea.REPORT_GENERATION)){
            procesadorTarea.procesarTarea(tareaDTO, new ReportGenerationStrategy());
        } else if (tipoTarea.equals(TipoTarea.TEXT_TRANSFORM)) {
            procesadorTarea.procesarTarea(tareaDTO, new TextTransformStrategy());
        }


        tareaDTO.setStatusTarea(StatusTarea.COMPLETADA);
        tareaRepository.save(MapperTarea.toEntity(tareaDTO));

    }
}
