package com.example.demo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventSubscriber<T extends IDomainEvent> {
    private final Class<T> type;
    private boolean called = false;

    public boolean wasCalled() {
        return called;
    }

    public void handle(T domainEvent) {
        called = true;
    }

    public boolean canHandle(IDomainEvent domainEvent) {
        return type.isAssignableFrom(domainEvent.getClass());
    }
}
