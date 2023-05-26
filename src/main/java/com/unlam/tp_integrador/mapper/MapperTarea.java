package com.unlam.tp_integrador.mapper;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MapperTarea {

    private static final String NEW_TAREA = "{} - Creando nueva tarea para el id: {} - {} - {}";


    public static TareaEntity newTarea(TareaDTO tareaDTO, String id){
      log.debug(NEW_TAREA, LoggingTag.MAPPER, id, LocalDateTime.now().withNano(0), MapperTarea.class.getSimpleName());
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
