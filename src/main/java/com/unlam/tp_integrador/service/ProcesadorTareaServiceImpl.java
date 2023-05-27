package com.unlam.tp_integrador.service;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.mapper.MapperTarea;
import com.unlam.tp_integrador.processor.ProcesadorTarea;
import com.unlam.tp_integrador.repositories.TareaRepository;
import com.unlam.tp_integrador.threads.ExecutorThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProcesadorTareaServiceImpl implements IProcesadorTareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private ProcesadorTarea procesadorTarea;

    @Override
    public TareaDTO crearTarea(TareaDTO tareaDTO) {
        String idTarea = procesadorTarea.generarRandomId();
        tareaRepository.save(MapperTarea.newTarea(tareaDTO, idTarea));
        tareaDTO.setId(idTarea);
        return tareaDTO;
    }

    @Override
    public void procesarTareaAsync(TareaDTO tareaDTO) {
        ExecutorThread executorThread = new ExecutorThread(tareaDTO, procesadorTarea, tareaRepository);
        executorThread.start();
    }

}
