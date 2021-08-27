package com.example.demo.domain;

import com.example.demo.domain.DomainEvent;
import com.example.demo.domain.EventPublisher;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventPublisher extends EventPublisher {
    List<DomainEvent> events;

    public InMemoryEventPublisher() {
        events = new ArrayList<>();
    }

    public InMemoryEventPublisher(List<DomainEvent> events) {
        this.events = events;
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        super.publish(domainEvent);
        events.add(domainEvent);
    }
}
