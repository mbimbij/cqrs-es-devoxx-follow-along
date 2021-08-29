package com.example.demo.domain;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AggregatePastEvents {
    private final int messageId;
    private final List<DomainEvent> events;

    public AggregatePastEvents(int messageId, Collection<DomainEvent> events) {
        this.messageId = messageId;
        this.events = new ArrayList<>(events);
    }

    public void add(DomainEvent event) {
        events.add(event);
    }

    public int getMessageId() {
        return messageId;
    }

    public Iterable<DomainEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }
}
