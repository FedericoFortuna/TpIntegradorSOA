package com.unlam.tp_integrador.communication;

import com.unlam.tp_integrador.tools.LoggingTag;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
@Slf4j
@ToString
public class MessageQueue {

    //ConcurrentLinkedQueue --> usa CAS, https://www.baeldung.com/java-concurrent-queues
    private static MessageQueue instance;
    private ConcurrentLinkedQueue<Message> queue;

    private static final String MQ_ALREADY_EXISTS = "{} - Cola de mensajes ya creado. Objeto {} - {}";
    private static final String MQ_NOT_EXISTS = "{} - Cola de mensajes inexistente - {}";
    private static final String MQ_ADD_MSG = "{} - Agregando mensaje con id: {} - {}";


    private MessageQueue() {
        queue = new ConcurrentLinkedQueue<>();
    }

    public static synchronized MessageQueue getInstance() {
        if (instance == null) {
            log.info(MQ_NOT_EXISTS, LoggingTag.CONFIGURATION, MessageQueue.class.getSimpleName());
            instance = new MessageQueue();
        }
        log.info(MQ_ALREADY_EXISTS, LoggingTag.CONFIGURATION, instance, MessageQueue.class.getSimpleName());
        return instance;
    }


    public void sendMessage(Message message) {
        log.info(MQ_ADD_MSG, LoggingTag.MQ, message.getRequestId(), MessageQueue.class.getSimpleName());
        queue.add(message);
    }

    public Message receiveMessage() {
        return queue.poll();
    }

    public boolean hasMessages() {
        return !queue.isEmpty();
    }
}
