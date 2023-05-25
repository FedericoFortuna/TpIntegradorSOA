package com.unlam.tp_integrador.service;

import com.unlam.tp_integrador.dto.TareaDTO;

public interface IProcesadorTareaService {
    TareaDTO crearTarea(TareaDTO tareaDTO);
    void procesarTareaAsync(TareaDTO tareaDTO);
}
