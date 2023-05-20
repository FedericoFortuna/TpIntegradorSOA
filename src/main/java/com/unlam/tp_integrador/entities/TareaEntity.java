package com.unlam.tp_integrador.entities;

import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "task")
public class TareaEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "task_type", nullable = false)
    private TipoTarea taskType;

    @Column(name = "task_details", nullable = false, columnDefinition = "text")
    private String detalleTarea;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarea status;

    @Column
    private String result;

}
