package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Timeline {
    private final List<TimelineMessage> messages = new ArrayList<>();

    public void handle(MessageQuacked messageQuacked) {
        messages.add(new TimelineMessage(messageQuacked.content()));
    }

    public List<TimelineMessage> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
