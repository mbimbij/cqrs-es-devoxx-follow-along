package com.example.demo;

import java.util.List;

public class Message {
    private boolean isDeleted = false;

    public Message(List<Object> history) {
        for (Object event : history) {
            if (event instanceof MessageDeleted) {
                apply((MessageDeleted) event);
            }
        }
    }

    private void apply(MessageDeleted messageDeleted) {
        isDeleted = true;
    }

    public static void quack(List<Object> history, String content) {
        history.add(new MessageQuacked(content));
    }

    public void delete(List<Object> history) {
        if (isDeleted) {
            return;
        }
        MessageDeleted event = new MessageDeleted();
        history.add(event);
        apply(event);
    }
}
