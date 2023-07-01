package com.unlam.tp_integrador.strategy.process;

import com.unlam.tp_integrador.dto.TareaDTO;

import java.util.HashMap;
import java.util.Map;

public class BraileTransformStrategy implements ProcesamientoStrategy {

    private final String MAP_KEY = "text";

    @Override
    public String process(TareaDTO tareaDTO) {
        String texto = String.valueOf(tareaDTO.getDetalleTarea().get(MAP_KEY));
        // Mapa de mapeo de caracteres a su representación en braille
        Map<Character, String> mapaBraille = new HashMap<>();
        mapaBraille.put(' ', "⠀");  // Espacio en blanco
        mapaBraille.put('a', "⠁");
        mapaBraille.put('b', "⠃");
        mapaBraille.put('c', "⠉");
        mapaBraille.put('d', "⠙");
        mapaBraille.put('e', "⠑");
        mapaBraille.put('f', "⠋");
        mapaBraille.put('g', "⠛");
        mapaBraille.put('h', "⠓");
        mapaBraille.put('i', "⠊");
        mapaBraille.put('j', "⠚");
        mapaBraille.put('k', "⠅");
        mapaBraille.put('l', "⠇");
        mapaBraille.put('m', "⠍");
        mapaBraille.put('n', "⠝");
        mapaBraille.put('o', "⠕");
        mapaBraille.put('p', "⠏");
        mapaBraille.put('q', "⠟");
        mapaBraille.put('r', "⠗");
        mapaBraille.put('s', "⠎");
        mapaBraille.put('t', "⠞");
        mapaBraille.put('u', "⠥");
        mapaBraille.put('v', "⠧");
        mapaBraille.put('w', "⠺");
        mapaBraille.put('x', "⠭");
        mapaBraille.put('y', "⠽");
        mapaBraille.put('z', "⠵");
        mapaBraille.put('0', "⠚");
        mapaBraille.put('1', "⠁");
        mapaBraille.put('2', "⠃");
        mapaBraille.put('3', "⠉");
        mapaBraille.put('4', "⠙");
        mapaBraille.put('5', "⠑");
        mapaBraille.put('6', "⠋");
        mapaBraille.put('7', "⠛");
        mapaBraille.put('8', "⠓");
        mapaBraille.put('9', "⠊");
        mapaBraille.put('.', "⠲");
        mapaBraille.put(',', "⠂");
        mapaBraille.put(';', "⠆");
        mapaBraille.put(':', "⠒");
        mapaBraille.put('!', "⠖");
        mapaBraille.put('?', "⠢");
        mapaBraille.put('(', "⠤");
        mapaBraille.put(')', "⠦");
        mapaBraille.put('-', "⠤");
        mapaBraille.put('+', "⠖");
        mapaBraille.put('*', "⠔");
        mapaBraille.put('/', "⠴");
        mapaBraille.put('=', "⠶");
        // Agregar más mapeos de caracteres a su representación en braille

        StringBuilder braille = new StringBuilder();
        for (char c : texto.toLowerCase().toCharArray()) {
            // Obtener la representación en braille del carácter
            String representacion = mapaBraille.getOrDefault(c, "");
            braille.append(representacion).append(" ");
        }

        return braille.toString();
    }

}
