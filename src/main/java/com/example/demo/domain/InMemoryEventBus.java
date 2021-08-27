package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventBus implements IPublishEvent {
    List<DomainEvent> events;
    List<ISubscribeToEvents> subscribers = new ArrayList<>();

    public InMemoryEventBus() {
        events = new ArrayList<>();
    }

    public InMemoryEventBus(List<DomainEvent> events) {
        this.events = events;
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        events.add(domainEvent);
        subscribers.stream()
                .filter(subscriber -> subscriber.accept(domainEvent.getClass()))
                .forEach(subscriber -> subscriber.handle(domainEvent));
    }

    @Override
    public void subscribe(ISubscribeToEvents<?> subscriber) {
        subscribers.add(subscriber);
    }

    public List<DomainEvent> getEvents() {
        return events;
    }
}
