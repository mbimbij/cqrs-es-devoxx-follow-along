package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    List<ISubscribeToEvents> subscribers = new ArrayList<>();

    public void publish(DomainEvent domainEvent) {
        subscribers.stream()
                .filter(subscriber -> subscriber.accept(domainEvent))
                .forEach(subscriber -> subscriber.handle(domainEvent));
    }

    public void subscribe(ISubscribeToEvents<?> subscriber) {
        subscribers.add(subscriber);
    }
}
