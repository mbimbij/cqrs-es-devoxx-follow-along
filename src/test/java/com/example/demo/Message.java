package com.example.demo;

import java.util.List;

public class Message {
    private boolean isDeleted = false;

    public Message(List<Object> history) {
        for (Object event : history) {
            if (event instanceof MessageDeleted) {
                this.isDeleted = true;
                break;
            }
        }
    }

    public static void quack(List<Object> history, String message) {
        history.add(new MessageQuacked(message));
    }

    public void delete(List<Object> history) {
        if(isDeleted) {
            return;
        }
        isDeleted = true;
        history.add(new MessageDeleted());
    }
}
