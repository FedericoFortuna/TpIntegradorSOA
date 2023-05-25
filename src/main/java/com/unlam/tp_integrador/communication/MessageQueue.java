package com.unlam.tp_integrador.communication;

import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MessageQueue {
    private Queue<Message> queue = new ConcurrentLinkedQueue<>();

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
