package com.unlam.tp_integrador.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.unlam.tp_integrador.enums.StatusTarea;
import com.unlam.tp_integrador.enums.TipoTarea;
import lombok.*;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class TareaDTO {

    @JsonIgnore
    private String id;

    @JsonProperty("task_type")
    private TipoTarea tipoTarea;

    @JsonProperty("task_details")
    private Map<String, Object> detalleTarea;

    @JsonIgnore
    private StatusTarea statusTarea;

    @JsonIgnore
    private String resultado;


    public String detalleTareaToString(){
        Gson gson = new Gson();
        return gson.toJson(detalleTarea);
    }

}
