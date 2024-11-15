package com.example.demo;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EventBus {
    private final IEventStream stream;
    private List<EventSubscriber<? extends IDomainEvent>> subscribers = new ArrayList<>();

    public void publish(IDomainEvent domainEvent) {
        stream.add(domainEvent);
        subscribers.forEach(s -> {
            @SuppressWarnings("unchecked")
            EventSubscriber<IDomainEvent> castedSubscriber = (EventSubscriber<IDomainEvent>) s;
            castedSubscriber.handle(domainEvent);
        });
    }

    public void subscribe(EventSubscriber<? extends IDomainEvent> subscriber) {
        subscribers.add(subscriber);
    }
}
