package com.example.demo;

import java.util.List;

public class Message {
    private boolean isDeleted = false;

    public Message(List<IDomainEvent> history) {
        for (IDomainEvent event : history) {
            apply(event);
        }
    }

    public static void quack(IEventStream history, String message) {
        history.add(new MessageQuacked(message));
    }

    public void delete(IEventStream history) {
        if(isDeleted) {
            return;
        }
        MessageDeleted messageDeleted = new MessageDeleted();
        history.add(messageDeleted);
        this.apply(messageDeleted);
    }

    private void apply(IDomainEvent event) {
        if (event instanceof MessageDeleted) {
            this.isDeleted = true;
        }
    }
}
