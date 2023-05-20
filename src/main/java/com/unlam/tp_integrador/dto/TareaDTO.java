package com.unlam.tp_integrador.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private TipoTarea tipoTarea;

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
