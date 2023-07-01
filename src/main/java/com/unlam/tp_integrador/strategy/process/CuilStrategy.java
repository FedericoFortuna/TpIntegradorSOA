package com.unlam.tp_integrador.strategy.process;

import com.unlam.tp_integrador.dto.TareaDTO;

public class CuilStrategy implements ProcesamientoStrategy{

    private final String MAP_KEY = "dni";

    private final String MAP_KEY_OPERATION = "genero";

    @Override
    public String process(TareaDTO tareaDTO) {
        return String.valueOf(tareaDTO.getDetalleTarea().get(MAP_KEY))+String.valueOf(tareaDTO.getDetalleTarea().get(MAP_KEY_OPERATION));
    }
}
