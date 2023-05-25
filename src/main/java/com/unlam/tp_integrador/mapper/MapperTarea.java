package com.unlam.tp_integrador.mapper;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import org.springframework.stereotype.Component;

@Component
public class MapperTarea {


    public static TareaEntity newTarea(TareaDTO tareaDTO, String id){
        return TareaEntity.builder()
                .id(id)
                .resultado("")
                .taskType(tareaDTO.getTipoTarea().toString())
                .status(StatusTarea.PENDIENTE.toString())
                .detalleTarea(tareaDTO.detalleTareaToString())
                .build();
    }

    public static TareaEntity toEntity(TareaDTO tareaDTO){
        return TareaEntity.builder()
                .id(tareaDTO.getId())
                .resultado("")
                .taskType(tareaDTO.getTipoTarea().toString())
                .status(StatusTarea.COMPLETADA.toString())
                .detalleTarea(tareaDTO.detalleTareaToString())
                .build();
    }

    public static TareaDTO toDTO(TareaEntity tareaEntity){
        return TareaDTO.builder()
                .id(tareaEntity.getId())
                .tipoTarea(TipoTarea.valueOf(tareaEntity.getTaskType()))
                .resultado(tareaEntity.getResultado())
                .statusTarea(StatusTarea.valueOf(tareaEntity.getStatus()))
                .detalleTarea(tareaEntity.detalleTareaToMap())
                .build();
    }

}
