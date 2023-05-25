package com.unlam.tp_integrador.processor;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.strategy.process.ProcesamientoStrategy;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Getter
public class ProcesadorTarea {


    public String generarRandomId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public void procesarTarea(TareaDTO tareaDTO, ProcesamientoStrategy strategy){
        tareaDTO.setResultado(strategy.process(tareaDTO));
    }


}
