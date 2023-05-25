package com.unlam.tp_integrador.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerRunner implements ApplicationRunner {
    @Autowired
    private MessageConsumer messageConsumer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Iniciar el hilo consumidor cuando la aplicación se levanta
        messageConsumer.consumeMessages();
    }
}