package com.example.demo;

public interface IEventPublisher {
    <T extends IDomainEvent> void publish(T domainEvent);
}
