package com.example.demo;

public interface IEventSubscriber<T extends DomainEvent> {
    void handle(T event);

    boolean accepts(Class<?> aClass);
}
