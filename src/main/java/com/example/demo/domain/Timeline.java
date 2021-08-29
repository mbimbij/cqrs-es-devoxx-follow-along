package com.example.demo.domain;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class Timeline implements ISubscribeToEvents<MessageQuacked> {
    private final int messageId;
    private List<TimelineMessage> messages = new ArrayList<>();

    @Override
    public void handle(MessageQuacked messageQuacked) {
        messages.add(new TimelineMessage(messageQuacked.getContent()));
    }

    @Override
    public boolean accept(DomainEvent event) {
        return event instanceof MessageQuacked && ((MessageQuacked) event).getMessageId() == messageId;
    }

    public Collection<TimelineMessage> getMessages() {
        return messages;
    }

}
