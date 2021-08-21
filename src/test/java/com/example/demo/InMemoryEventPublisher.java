package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventPublisher implements IPublishEvent {
    List<DomainEvent> events = new ArrayList<>();

    @Override
    public void publish(DomainEvent domainEvent) {
        events.add(domainEvent);
    }

    public List<DomainEvent> getEvents() {
        return events;
    }
}
