package com.example.demo;

import java.util.List;

public class Message {
    public void quack(List<Object> history, String content) {
        history.add(new MessageQuacked(content));
    }

    public void delete(List<Object> history) {
        history.add(new MessageDeleted());
    }
}
