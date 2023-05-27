package com.unlam.tp_integrador.processor;

import com.unlam.tp_integrador.communication.Message;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.repositories.TareaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ProcesadorMensaje {

    @Autowired
    private TareaRepository tareaRepository;


    public synchronized void procesarMensaje(Message message){
        tareaRepository.actualizarEstadoYResultadoPorId(
                message.getRequestId(),
                StatusTarea.COMPLETADA.toString(),
                message.getResultado());
    }
}
