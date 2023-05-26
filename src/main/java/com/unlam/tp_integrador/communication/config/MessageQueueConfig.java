package com.unlam.tp_integrador.communication.config;

import com.unlam.tp_integrador.communication.MessageQueue;
import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class MessageQueueConfig {
    private final String CREATING_MQ = "{} - Creando cola de mensajes: {} - {}";
    @Bean
    public MessageQueue messageQueue() {

        log.info(CREATING_MQ, LoggingTag.CONFIGURATION, LocalDateTime.now().withNano(0), MessageQueueConfig.class.getSimpleName());
        return new MessageQueue();
    }

}
