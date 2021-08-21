package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AggregatePastEvents {
    private List<DomainEvent> events = new ArrayList<>();

    public AggregatePastEvents(Collection<DomainEvent> events) {
        this.events = new ArrayList<>(events);
    }

    public void add(DomainEvent event) {
        events.add(event);
    }

    public Iterable<DomainEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }
}
