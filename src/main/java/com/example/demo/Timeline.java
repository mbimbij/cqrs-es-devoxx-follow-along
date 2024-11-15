package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Timeline extends EventSubscriber<MessageQuacked> {
    private final List<TimelineMessage> messages = new ArrayList<>();

    public Timeline(Class<MessageQuacked> type) {
        super(type);
    }

    public void handle(MessageQuacked messageQuacked) {
        messages.add(new TimelineMessage(messageQuacked.content()));
    }

    public List<TimelineMessage> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
