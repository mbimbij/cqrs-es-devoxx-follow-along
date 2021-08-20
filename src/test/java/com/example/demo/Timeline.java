package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Timeline {
    private List<TimelineMessage> messages = new ArrayList<>();

    public Iterable<TimelineMessage> getMessages() {
        return messages;
    }

    public void handle(MessageQuacked messageQuacked) {
        messages.add(new TimelineMessage(messageQuacked.getContent()));
    }
}
