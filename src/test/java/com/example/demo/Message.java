package com.example.demo;

public class Message {
    private boolean isDeleted = false;

    public Message(IStreamEvents eventsStream) {
        for (Object event : eventsStream.getEvents()) {
            if (event instanceof MessageDeleted) {
                apply((MessageDeleted) event);
            }
        }
    }

    private void apply(MessageDeleted messageDeleted) {
        isDeleted = true;
    }

    public static void quack(IStreamEvents eventsStream, String content) {
        eventsStream.add(new MessageQuacked(content));
    }

    public void delete(IStreamEvents eventStream) {
        if (isDeleted) {
            return;
        }
        MessageDeleted event = new MessageDeleted();
        eventStream.add(event);
        apply(event);
    }
}
