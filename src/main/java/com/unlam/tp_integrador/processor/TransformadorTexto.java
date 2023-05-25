package com.unlam.tp_integrador.processor;

import com.unlam.tp_integrador.strategy.text.OperationStrategy;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TransformadorTexto {


    public String transformar(String texto, OperationStrategy strategy){
        return strategy.transform(texto);
    }


}
