package com.example.demo;

public class EventSubscriber<T extends IDomainEvent> {

    private boolean called = false;

    public boolean wasCalled() {
        return called;
    }

    public void handle(T domainEvent) {
        called = true;
    }
}
