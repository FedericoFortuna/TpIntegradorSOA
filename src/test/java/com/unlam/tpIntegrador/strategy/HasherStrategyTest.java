package com.unlam.tpIntegrador.strategy;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.strategy.process.HasherStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

public class HasherStrategyTest {

    private HasherStrategy hasherStrategy;

    private Integer EXPECTED_LENGHT = 44;

    @BeforeEach
    public void setup() {
        hasherStrategy = new HasherStrategy();
    }

    @Test
    public void testProcess() {
        // Crear un objeto TareaDTO para el caso de prueba
        TareaDTO tareaDTO = new TareaDTO();
        Map<String, Object> detalleTarea = new HashMap<>();
        detalleTarea.put("text", "password1234");
        tareaDTO.setDetalleTarea(detalleTarea);

        // Realizar el procesamiento y obtener el resultado
        String result = hasherStrategy.process(tareaDTO);

        // Verificar que el resultado no sea nulo
        Assertions.assertNotNull(result);

        // Verificar que el resultado sea un string
        Assertions.assertTrue(result instanceof String);

        // Verificar que el resultado tenga la longitud esperada
        Assertions.assertEquals(EXPECTED_LENGHT, result.length());
    }
}