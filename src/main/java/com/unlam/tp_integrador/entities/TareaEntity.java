package com.unlam.tp_integrador.entities;

import com.google.gson.Gson;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.Map;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "task")
public class TareaEntity {

    @Id
    private String id;

    @Column(name = "task_type", nullable = false)
    private String taskType;

    @Column(name = "task_details", nullable = false, columnDefinition = "text")
    private String detalleTarea;


    @Column(nullable = false, length = 25)
    private String status;

    @Column(length = 100)
    private String resultado;


    public Map<String, Object> detalleTareaToMap(){
        Gson gson = new Gson();
        Type type = new com.google.gson.reflect.TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(this.detalleTarea, type);
    }

}
