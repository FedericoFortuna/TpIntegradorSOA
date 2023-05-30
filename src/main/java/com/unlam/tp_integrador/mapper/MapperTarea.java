package com.unlam.tp_integrador.mapper;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import com.unlam.tp_integrador.response.TareaResponse;
import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MapperTarea {

    private static final String NEW_TAREA = "{} - Creando nueva tarea para el id: {} - {} - {}";
    private static final String TO_ENTITY = "{} - Mappeando TareaDto a TareaEntity para tarea con el id: {} - {} - {}";
    private static final String TO_DTO = "{} - Mappeando TareaEntity a TareaDto para tarea con el id: {} - {} - {}";
    private static final String TO_RESPONSE = "{} - Mappeando TareaEntity a TareaResponse para tarea con el id: {} - {} - {}";


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
        log.debug(TO_ENTITY, LoggingTag.MAPPER, tareaDTO.getId(), LocalDateTime.now().withNano(0), MapperTarea.class.getSimpleName());
        return TareaEntity.builder()
                .id(tareaDTO.getId())
                .resultado("")
                .taskType(tareaDTO.getTipoTarea().toString())
                .status(tareaDTO.getStatusTarea().toString())
                .detalleTarea(tareaDTO.detalleTareaToString())
                .build();
    }

    public static TareaDTO toDTO(TareaEntity tareaEntity){
        log.debug(TO_DTO, LoggingTag.MAPPER, tareaEntity.getId(), LocalDateTime.now().withNano(0), MapperTarea.class.getSimpleName());
        return TareaDTO.builder()
                .id(tareaEntity.getId())
                .tipoTarea(TipoTarea.valueOf(tareaEntity.getTaskType()))
                .resultado(tareaEntity.getResultado())
                .statusTarea(StatusTarea.valueOf(tareaEntity.getStatus()))
                .detalleTarea(tareaEntity.detalleTareaToMap())
                .build();
    }

    public static TareaResponse toResponse(TareaEntity tareaEntity){
        log.debug(TO_RESPONSE, LoggingTag.MAPPER, tareaEntity.getId(), LocalDateTime.now().withNano(0), MapperTarea.class.getSimpleName());
        return TareaResponse.builder()
                .id(tareaEntity.getId())
                .resultado(tareaEntity.getResultado())
                .build();
    }




}
