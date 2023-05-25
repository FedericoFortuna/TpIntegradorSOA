package com.unlam.tp_integrador.communication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private MessageQueue mq;

    public void sendMessage(Message message){
        mq.sendMessage(Message.builder()
                .requestId(message.getRequestId())
                .resultado(message.getResultado())
                .build());
    }


}
