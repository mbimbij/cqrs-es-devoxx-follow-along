package com.example.demo;

import java.util.List;

public class Message {
    public static void quack(List<Object> history, String message) {
        history.add(new MessageQuacked(message));
    }

    public void delete(List<Object> history) {
        history.add(new MessageDeleted());
    }
}
