package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Timeline {
    private List<TimelineMessage> messages = new ArrayList<>();

    public void handle(MessageQuacked messageQuacked) {
        messages.add(new TimelineMessage(messageQuacked.getContent()));
    }

    public Collection<TimelineMessage> getMessages() {
        return messages;
    }
}
