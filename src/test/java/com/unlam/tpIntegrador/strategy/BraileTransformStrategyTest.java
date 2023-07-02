package com.unlam.tpIntegrador.strategy;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.strategy.process.BraileTransformStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BraileTransformStrategyTest {

    private BraileTransformStrategy braileTransformStrategy;

    private final String BRAILE_EXPECTED = "⠓ ⠑ ⠇ ⠇ ⠕ ⠀ ⠺ ⠕ ⠗ ⠇ ⠙ ";

    @BeforeEach
    public void setup() {
        braileTransformStrategy = new BraileTransformStrategy();
    }

    @Test
    public void testProcess() {
        // Crear un objeto TareaDTO para el caso de prueba
        TareaDTO tareaDTO = new TareaDTO();
        Map<String, Object> detalleTarea = new HashMap<>();
        detalleTarea.put("text", "Hello World");
        tareaDTO.setDetalleTarea(detalleTarea);

        // Realizar el procesamiento y obtener el resultado
        String result = braileTransformStrategy.process(tareaDTO);

        // Verificar que el resultado no sea nulo
        Assertions.assertNotNull(result);

        // Verificar que el resultado sea un string
        Assertions.assertTrue(result instanceof String);

        // Verificar el resultado esperado
        Assertions.assertEquals(BRAILE_EXPECTED, result);
    }
}

