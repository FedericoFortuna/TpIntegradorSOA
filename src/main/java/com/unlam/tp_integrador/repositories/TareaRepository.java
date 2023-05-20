package com.unlam.tp_integrador.repositories;

import com.unlam.tp_integrador.entities.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, String> {
}
