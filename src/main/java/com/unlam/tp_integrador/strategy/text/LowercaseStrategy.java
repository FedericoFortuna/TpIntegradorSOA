package com.unlam.tp_integrador.strategy.text;

public class LowercaseStrategy implements OperationStrategy{
    @Override
    public String transform(String text) {
        return text.toLowerCase();
    }
}
