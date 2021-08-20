package com.example.demo;

public class Message {
    private final DecisionProjection projection;

    public Message(IStreamEvents history) {
        projection = new DecisionProjection(history);
    }

    public static void quack(IStreamEvents history, String content) {
        history.add(new MessageQuacked(content));
    }

    public void delete(IStreamEvents history) {
        if (projection.isDeleted()) {
            return;
        }
        MessageDeleted event = new MessageDeleted();
        publishAndApply(history, event);
    }

    private void publishAndApply(IStreamEvents history, MessageDeleted event) {
        history.add(event);
        projection.apply(event);
    }
}
