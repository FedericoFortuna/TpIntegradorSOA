package com.unlam.tp_integrador.communication.config;

import com.unlam.tp_integrador.communication.MessageQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Bean
    public MessageQueue messageQueue() {
        return new MessageQueue();
    }

}
