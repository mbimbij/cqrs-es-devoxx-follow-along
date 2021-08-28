package com.example.demo.domain;

public interface ISubscribeToEvents<T extends DomainEvent> {
    void handle(T event);

    boolean accept(DomainEvent event);
}
