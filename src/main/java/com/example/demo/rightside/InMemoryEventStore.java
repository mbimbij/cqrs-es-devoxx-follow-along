package com.example.demo.rightside;

import com.example.demo.domain.AggregatePastEvents;
import com.example.demo.domain.DomainEvent;
import com.example.demo.domain.EventStore;

import java.util.*;

public class InMemoryEventStore implements EventStore {
    private Map<Integer, Collection<DomainEvent>> events = new HashMap<>();

    @Override
    public void handle(DomainEvent event) {
        int messageId = event.getMessageId();
        events.putIfAbsent(messageId, new ArrayList<>());
        events.get(messageId).add(event);
    }

    @Override
    public boolean accept(DomainEvent event) {
        return true;
    }


    @Override
    public AggregatePastEvents getPastEvents(int messageId) {
        return new AggregatePastEvents(messageId, events.getOrDefault(messageId, Collections.emptyList()));
    }
}
