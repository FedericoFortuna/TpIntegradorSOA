package com.unlam.tp_integrador.strategy.process;

import com.unlam.tp_integrador.dto.TareaDTO;
import com.unlam.tp_integrador.enums.OperacionTexto;
import com.unlam.tp_integrador.processor.TransformadorTexto;
import com.unlam.tp_integrador.strategy.text.LowercaseStrategy;
import com.unlam.tp_integrador.strategy.text.UppercaseStrategy;

public class TextTransformStrategy implements ProcesamientoStrategy {


    private TransformadorTexto transformadorTexto = new TransformadorTexto();

    private final String MAP_KEY_TEXT = "text";
    private final String MAP_KEY_OPERATION = "operation";

    @Override
    public String process(TareaDTO tareaDTO) {
        String text = tareaDTO.getDetalleTarea().get(MAP_KEY_TEXT).toString();
        String operation = tareaDTO.getDetalleTarea().get(MAP_KEY_OPERATION).toString().toUpperCase();
        String resultado = "";

        if (operation.equals(OperacionTexto.UPPERCASE.toString())) {
            resultado = transformadorTexto.transformar(text, new UppercaseStrategy());
        } else if (operation.equals(OperacionTexto.LOWERCASE.toString())) {
            resultado = transformadorTexto.transformar(text, new LowercaseStrategy());
        }

        return resultado;
    }


}
