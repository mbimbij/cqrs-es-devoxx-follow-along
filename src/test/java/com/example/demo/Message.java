package com.example.demo;

public class Message {
    public static void quack(IPublishEvent eventPublisher, String content) {
        eventPublisher.publish(new MessageQuacked(content));
    }

    public void delete(IPublishEvent eventPublisher) {
        eventPublisher.publish(new MessageDeleted());
    }
}
