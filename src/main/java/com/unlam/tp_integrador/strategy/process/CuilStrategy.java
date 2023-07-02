package com.unlam.tp_integrador.strategy.process;

import com.unlam.tp_integrador.dto.TareaDTO;

import java.util.Objects;

public class CuilStrategy implements ProcesamientoStrategy {

    private final String MAP_KEY = "text";

    private final String MAP_KEY_OPERATION = "operation";

    private final String MASCULINO = "M";
    private final String FEMENINO = "F";

    private final Integer[] coeficientes = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};

    private final Integer PREFIJO_TMP_M = 20;
    private final Integer PREFIJO_TMP_F = 27;

    private final Integer RESTO_VERIFICADOR = 1;
    private final Integer PREFIJO_TMP_MIXTO = 23;

    private final Integer DIVISOR_VERIFICADOR = 11;

    @Override
    public String process(TareaDTO tareaDTO) {

        String dni = tareaDTO.getDetalleTarea().get(MAP_KEY).toString();
        String genero = tareaDTO.getDetalleTarea().get(MAP_KEY_OPERATION).toString();

        int i = 0;
        int sumador = 0;

        int primerParte = 0;

        if (Objects.equals(genero, MASCULINO))
            primerParte = PREFIJO_TMP_M;
        else if (Objects.equals(genero, FEMENINO))
            primerParte = PREFIJO_TMP_F;

        String casiCuil = primerParte + dni;

        for (char ch : casiCuil.toCharArray()) {
            sumador += Integer.parseInt(String.valueOf(ch)) * coeficientes[i];
            i++;
        }

        int resto = sumador % DIVISOR_VERIFICADOR;

        int digitoVerificador;

        if (resto == 0)
            digitoVerificador = 0;
        else if (Objects.equals(genero, MASCULINO) && resto == RESTO_VERIFICADOR) {
            digitoVerificador = 9;
            primerParte = PREFIJO_TMP_MIXTO;
        } else if (Objects.equals(genero, FEMENINO) && resto == RESTO_VERIFICADOR) {
            digitoVerificador = 4;
            primerParte = PREFIJO_TMP_MIXTO;
        } else
            digitoVerificador = DIVISOR_VERIFICADOR - resto;

        return primerParte + "-" + dni + "-" + digitoVerificador;
    }
}
