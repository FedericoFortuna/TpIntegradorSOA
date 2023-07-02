package com.unlam.tpIntegrador.strategy;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.strategy.process.CuilStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class CuilStrategyTest {

    private CuilStrategy cuilStrategy;

    private final String DNI_KEY = "dni";
    private final String GENERO_KEY = "genero";
    @Mock
    private TareaDTO tareaDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cuilStrategy = new CuilStrategy();
    }

    @Test
    public void testCuilStrategyWithMaleGender() {
        String dni = "12345678";
        String genero = "M";

        Map<String, Object> detalleTarea = new HashMap<>();
        detalleTarea.put(DNI_KEY, dni);
        detalleTarea.put(GENERO_KEY, genero);

        Mockito.when(tareaDTO.getDetalleTarea()).thenReturn(detalleTarea);

        String result = cuilStrategy.process(tareaDTO);

        String expected = "20-12345678-6";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCuilStrategyWithFemaleGender() {
        String dni = "42952970";
        String genero = "F";

        Map<String, Object> detalleTarea = new HashMap<>();
        detalleTarea.put(DNI_KEY, dni);
        detalleTarea.put(GENERO_KEY, genero);

        Mockito.when(tareaDTO.getDetalleTarea()).thenReturn(detalleTarea);

        String result = cuilStrategy.process(tareaDTO);

        String expected = "27-42952970-6";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testCuilStrategyWithMixedGender() {
        String dni = "40937258";
        String genero = "M";

        Map<String, Object> detalleTarea = new HashMap<>();
        detalleTarea.put(DNI_KEY, dni);
        detalleTarea.put(GENERO_KEY, genero);

        Mockito.when(tareaDTO.getDetalleTarea()).thenReturn(detalleTarea);

        String result = cuilStrategy.process(tareaDTO);

        String expected = "23-40937258-9";
        Assertions.assertEquals(expected, result);
    }
}
