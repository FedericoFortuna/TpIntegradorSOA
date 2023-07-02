package com.unlam.tpIntegrador.mapper;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.entities.TareaEntity;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import com.unlam.tp_integrador.mapper.MapperTarea;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MapperTareaTest {

    private TareaDTO tareaDTOForNewTarea;
    private TareaDTO tareaDTO;
    private String dummyId;
    private Map<String, Object> dummyMap;


    @Before
    public void setUp(){
        dummyMap = new HashMap<>();
        dummyId = "dummyId";
        String keyMap = "dummyKey";
        ArrayList<Integer> dummyObj = new ArrayList<>();
        dummyMap.put(keyMap, dummyObj);

        tareaDTOForNewTarea = TareaDTO.builder()
                .tipoTarea(TipoTarea.HASH_PW)
                .detalleTarea(dummyMap)
                .build();

        tareaDTO = TareaDTO.builder()
                .id(dummyId)
                .tipoTarea(TipoTarea.HASH_PW)
                .detalleTarea(dummyMap)
                .statusTarea(StatusTarea.EN_PROGRESO)
                .build();
    }


    @Test
    public void createNewTarea_ShouldReturnTareaEntity(){
        TareaEntity tareaEntity = MapperTarea.newTarea(tareaDTOForNewTarea, dummyId);
        assertEquals(dummyId, tareaEntity.getId());
        assertEquals(StatusTarea.PENDIENTE.toString(), tareaEntity.getStatus());
    }


    @Test
    public void mapToEntity_ShouldReturnTareaEntity(){
        TareaEntity tareaEntity = MapperTarea.toEntity(tareaDTO);
        assertEquals(tareaDTO.getId(), tareaEntity.getId());
        assertEquals(tareaDTO.getTipoTarea().toString(), tareaEntity.getTaskType());
        assertEquals(tareaDTO.getStatusTarea().toString(), tareaEntity.getStatus());
        assertEquals(tareaDTO.detalleTareaToString(), tareaEntity.getDetalleTarea());
    }




}
