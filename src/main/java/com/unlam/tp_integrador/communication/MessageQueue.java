package com.unlam.tp_integrador.communication;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {
    //ConcurrentLinkedQueue --> usa CAS, https://www.baeldung.com/java-concurrent-queues
    private Queue<Message> queue;

    public MessageQueue(){
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public void sendMessage(Message message) {
        queue.add(message);
    }

    public Message receiveMessage() {
        return queue.poll();
    }

    public boolean hasMessages() {
        return !queue.isEmpty();
    }
}
