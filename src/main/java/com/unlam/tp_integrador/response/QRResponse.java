package com.unlam.tp_integrador.response;

import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QRResponse {
    private String url;
    private String qrCodeImage;
}
