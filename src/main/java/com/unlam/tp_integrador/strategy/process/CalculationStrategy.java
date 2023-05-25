package com.unlam.tp_integrador.strategy.process;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.tools.Utils;

import java.util.ArrayList;

public class CalculationStrategy implements ProcesamientoStrategy {

    private final String MAP_KEY = "numbers";

    @Override
    public String process(TareaDTO tareaDTO) {

        ArrayList<Integer> numbers = Utils.objectToArray(tareaDTO.getDetalleTarea().get(MAP_KEY));
        Integer resultado = 0;
        for (Integer number : numbers) {
            if (number > resultado) {
                resultado = number;
            }
        }

        return resultado.toString();
    }
}
