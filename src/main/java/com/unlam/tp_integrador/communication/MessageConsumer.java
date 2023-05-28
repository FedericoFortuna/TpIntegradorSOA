package com.unlam.tp_integrador.communication;

import com.unlam.tp_integrador.processor.ProcesadorMensaje;
import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableAsync
@Slf4j
public class MessageConsumer {


    private final String PROCESSING_MESSAGE = "{} - Inicio: {} - procesando mensaje con id: {} - {}";
    private final String QUEUE_WITH_MSG = "{} - COLA CON MENSAJES DENTRO. {} - {}";


    private final MessageQueue messageQueue = MessageQueue.getInstance();

    @Autowired
    private ProcesadorMensaje procesadorMensaje;

    @Async
    public void consumeMessages() throws InterruptedException {

        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(500);
            if (messageQueue.hasMessages()) {
                log.info(QUEUE_WITH_MSG, LoggingTag.MESSAGE_CONSUMER, LocalDateTime.now().withNano(0), MessageConsumer.class.getSimpleName());
                Message message = messageQueue.receiveMessage();
                log.info(PROCESSING_MESSAGE, LoggingTag.MESSAGE_CONSUMER, LocalDateTime.now().withNano(0), message.getRequestId(), MessageConsumer.class.getSimpleName());

                procesadorMensaje.procesarMensaje(message);
            }
            if (isInterrupted()) {
                break;
            }
        }
    }

    public static boolean isInterrupted(){
        return Thread.interrupted();
    }

}
