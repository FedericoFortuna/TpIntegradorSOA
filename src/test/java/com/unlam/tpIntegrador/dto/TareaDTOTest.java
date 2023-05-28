package com.unlam.tpIntegrador.dto;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.enums.TipoTarea;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TareaDTOTest {

    private TareaDTO dto;

    private final String MAP_KEY = "numbers";

    @Before
    public void setUp(){
        Map<String, Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        map.put(MAP_KEY, list);

        dto = TareaDTO.builder()
                .tipoTarea(TipoTarea.CALCULATION)
                .detalleTarea(map)
                .build();
    }

    @Test
    public void castTaskDetailsToString_ShouldBeWork(){
        assertThat(dto.detalleTareaToString()).isInstanceOf(String.class);
    }


}
