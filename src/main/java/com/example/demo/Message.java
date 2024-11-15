package com.example.demo;

import java.util.List;

public class Message {
    private final DecisionProjection decisionProjection = new DecisionProjection();

    public Message(List<IDomainEvent> history) {
        for (IDomainEvent event : history) {
            apply(event);
        }
    }

    public static void quack(EventBus eventBus, String message) {
        eventBus.publish(new MessageQuacked(message));
    }

    public void delete(IEventStream history) {
        if(decisionProjection.isDeleted()) {
            return;
        }
        MessageDeleted messageDeleted = new MessageDeleted();
        history.add(messageDeleted);
        this.apply(messageDeleted);
    }

    private void apply(IDomainEvent event) {
        decisionProjection.apply(event);
    }
}
