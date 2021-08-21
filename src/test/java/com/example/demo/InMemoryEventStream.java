package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventStream implements IPublishEvent {
    List<DomainEvent> events = new ArrayList<>();

    @Override
    public void publish(DomainEvent domainEvent) {
        events.add(domainEvent);
    }

    public List<DomainEvent> getEvents() {
        return events;
    }
}
