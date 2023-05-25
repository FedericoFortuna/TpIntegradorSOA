package com.unlam.tp_integrador.repositories;

import com.unlam.tp_integrador.entities.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, String> {

    @Modifying
    @Query("UPDATE TareaEntity t SET t.status = :estado, t.resultado = :resultado WHERE t.id = :id")
    void actualizarEstadoYResultadoPorId(String id, String estado, String resultado);
}
