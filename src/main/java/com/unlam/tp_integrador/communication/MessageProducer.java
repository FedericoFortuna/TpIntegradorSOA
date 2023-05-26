package com.unlam.tp_integrador.communication;


import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MessageProducer {

    @Autowired
    private MessageQueue mq;

    private final String SEND_MESSAGE = "{} - Envio: {} - enviando mensaje con id: {} - Hilo: {} - {}";

    public void sendMessage(Message message) {
        log.info(SEND_MESSAGE, LoggingTag.MESSAGE_PRODUCER, LocalDateTime.now().withNano(0), message.getRequestId(), Thread.currentThread().getName(), MessageProducer.class.getSimpleName());
        mq.sendMessage(Message.builder()
                .requestId(message.getRequestId())
                .resultado(message.getResultado())
                .build());
    }


}
