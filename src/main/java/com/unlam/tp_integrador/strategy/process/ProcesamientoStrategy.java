package com.unlam.tp_integrador.strategy.process;

import com.unlam.tp_integrador.dto.TareaDTO;

public interface ProcesamientoStrategy {
    String process(TareaDTO tareaDTO);
}
