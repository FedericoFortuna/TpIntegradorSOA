package com.unlam.tpIntegrador.strategy;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.strategy.process.CalculationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CalculationStrategyTest {

    private CalculationStrategy calculationStrategy;

    private final Integer MAX_VALUE = 555;

    @BeforeEach
    public void setup() {
        calculationStrategy = new CalculationStrategy();
    }

    @Test
    public void testProcess() {

        TareaDTO tareaDTO = new TareaDTO();
        Map<String, Object> detalleTarea = new HashMap<>();
        detalleTarea.put("numbers", new ArrayList<>(Arrays.asList(10, 5, 20, 15, MAX_VALUE)));
        tareaDTO.setDetalleTarea(detalleTarea);

        // Realizar el procesamiento y obtener el resultado
        String result = calculationStrategy.process(tareaDTO);

        // Verificar que el resultado no sea nulo
        Assertions.assertNotNull(result);

        // Verificar que el resultado sea un string
        Assertions.assertTrue(result instanceof String);

        // Verificar el resultado esperado
        Assertions.assertEquals(MAX_VALUE.toString(), result);
    }
}