package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Timeline implements ISubscribeToEvents<MessageQuacked> {
    private List<TimelineMessage> messages = new ArrayList<>();

    @Override
    public void handle(MessageQuacked messageQuacked) {
        messages.add(new TimelineMessage(messageQuacked.getContent()));
    }

    public Collection<TimelineMessage> getMessages() {
        return messages;
    }

    @Override
    public boolean accept(Class<?> clazz) {
        return MessageQuacked.class.equals(clazz);
    }
}
