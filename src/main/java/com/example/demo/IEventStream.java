package com.example.demo;

public interface IEventStream {
    void add(IDomainEvent event);
}
