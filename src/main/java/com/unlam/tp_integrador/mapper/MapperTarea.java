package com.unlam.tp_integrador.mapper;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.enums.StatusTarea;
import org.springframework.stereotype.Component;

@Component
public class MapperTarea {


    public static TareaEntity newTarea(TareaDTO tareaDTO, String id){
        return TareaEntity.builder()
                .id(id)
                .result("")
                .taskType(tareaDTO.getTipoTarea())
                .status(StatusTarea.PENDIENTE)
                .detalleTarea(tareaDTO.detalleTareaToString())
                .build();
    }

    public static TareaEntity toEntity(TareaDTO tareaDTO){
        return TareaEntity.builder()
                .id(tareaDTO.getId())
                .result("")
                .taskType(tareaDTO.getTipoTarea())
                .status(StatusTarea.COMPLETADA)
                .detalleTarea(tareaDTO.detalleTareaToString())
                .build();
    }

}
