package com.unlam.tp_integrador.strategy.text;

public class UppercaseStrategy implements OperationStrategy{
    @Override
    public String transform(String text) {
        return text.toUpperCase();
    }
}
