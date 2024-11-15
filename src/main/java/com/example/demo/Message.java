package com.example.demo;

public class Message {
    private final DecisionProjection decisionProjection = new DecisionProjection();

    public Message(IEventStream stream) {
        for (IDomainEvent event : stream.getEvents()) {
            apply(event);
        }
    }

    public static void quack(IEventPublisher eventBus, String message) {
        eventBus.publish(new MessageQuacked(message));
    }

    public void delete(IEventPublisher publisher) {
        if(decisionProjection.isDeleted()) {
            return;
        }
        MessageDeleted messageDeleted = new MessageDeleted();
        publisher.publish(messageDeleted);
        this.apply(messageDeleted);
    }

    private void apply(IDomainEvent event) {
        decisionProjection.apply(event);
    }
}
