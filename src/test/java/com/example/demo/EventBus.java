package com.example.demo;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class EventBus {
    private final IStreamEvents eventStream;
    List<IEventSubscriber> subscribers = new ArrayList<>();

    public <T extends DomainEvent> void publish(T event) {
        eventStream.add(event);
        subscribers.stream()
                .filter(eventSubscriber -> eventSubscriber.accepts(event.getClass()))
                .forEach(iEventSubscriber -> iEventSubscriber.handle(event));
    }

    public void subscribe(IEventSubscriber<? extends DomainEvent> subscriber) {
        subscribers.add(subscriber);
    }
}
