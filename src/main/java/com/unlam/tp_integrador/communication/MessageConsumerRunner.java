package com.unlam.tp_integrador.communication;

import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MessageConsumerRunner implements ApplicationRunner {
    @Autowired
    private MessageConsumer messageConsumer;

    private final String START_CONSUMMER = "{} - Hilo consumidor. Inicio: {} - {}";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Iniciar el hilo consumidor cuando la aplicación se levanta
        log.info(START_CONSUMMER, LoggingTag.MESSAGE_CONSUMER, LocalDateTime.now().withNano(0), MessageConsumer.class.getSimpleName());
        messageConsumer.consumeMessages();
    }
}