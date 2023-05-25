package com.unlam.tp_integrador.communication;

import com.unlam.tp_integrador.processor.ProcesadorMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class MessageConsumer {

    @Autowired
    private MessageQueue messageQueue;

    @Autowired
    private ProcesadorMensaje procesadorMensaje;

    @Async
    public void consumeMessages(){
        while (true) {
            if (messageQueue.hasMessages()) {
                Message message = messageQueue.receiveMessage();
                // Procesar el mensaje y actualizar el estado en la base de datos
                procesadorMensaje.procesarMensaje(message);
            }
        }
    }
}
