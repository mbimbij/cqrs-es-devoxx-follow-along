package com.example.demo;

public class Message {
    private final DecisionProjection projection;

    public Message(IStreamEvents history) {
        projection = new DecisionProjection(history);
    }

    public static void quack(IPublishEvent eventPublisher, String content) {
        eventPublisher.publish(new MessageQuacked(content));
    }

    public void delete(IPublishEvent eventPublisher) {
        if (projection.isDeleted()) {
            return;
        }
        MessageDeleted event = new MessageDeleted();
        publishAndApply(eventPublisher, event);
    }

    private void publishAndApply(IPublishEvent eventPublisher, MessageDeleted event) {
        eventPublisher.publish(event);
        projection.apply(event);
    }
}
