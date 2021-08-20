package com.example.demo;

public interface IStreamEvents {
    void add(DomainEvent event);

    Iterable<DomainEvent> getEvents();
}
