package com.example.demo;

import java.util.List;

public class Message {
    private boolean isDeleted = false;

    public Message(List<Object> history) {
        for (Object event : history) {
            if (event instanceof MessageDeleted) {
                isDeleted = true;
                break;
            }
        }
    }

    public static void quack(List<Object> history, String content) {
        history.add(new MessageQuacked(content));
    }

    public void delete(List<Object> history) {
        if (isDeleted) {
            return;
        }
        history.add(new MessageDeleted());
    }
}
