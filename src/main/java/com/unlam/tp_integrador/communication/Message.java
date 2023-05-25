package com.unlam.tp_integrador.communication;

import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String requestId;
    private String resultado;
}
