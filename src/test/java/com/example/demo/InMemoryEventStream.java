package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventStream implements IStreamEvents {
    private List<DomainEvent> events = new ArrayList<>();

    @Override
    public void add(DomainEvent event) {
        events.add(event);
    }

    @Override
    public Iterable<DomainEvent> getEvents() {
        return events;
    }
}
