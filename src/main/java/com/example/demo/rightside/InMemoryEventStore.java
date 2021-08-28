package com.example.demo.rightside;

import com.example.demo.domain.AggregatePastEvents;
import com.example.demo.domain.DomainEvent;
import com.example.demo.domain.EventStore;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventStore implements EventStore {
    private List<DomainEvent> events = new ArrayList<>();

    @Override
    public void handle(DomainEvent event) {
        events.add(event);
    }

    @Override
    public boolean accept(DomainEvent event) {
        return true;
    }


    @Override
    public AggregatePastEvents getPastEvents() {
        return new AggregatePastEvents(events);
    }
}
