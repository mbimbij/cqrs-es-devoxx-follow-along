package com.example.demo;

public class Message {
    public static void quack(IPublishEvent inMemoryEventBus, String content) {
        inMemoryEventBus.publish(new MessageQuacked(content));
    }
}
