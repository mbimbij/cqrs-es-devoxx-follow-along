package com.example.demo;

public interface ISubscribeToEvents<T extends DomainEvent> {
    void handle(T event);

    boolean accept(Class<?> clazz);
}
