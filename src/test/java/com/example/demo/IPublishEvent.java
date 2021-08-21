package com.example.demo;

public interface IPublishEvent {
    void publish(DomainEvent domainEvent);

    void subscribe(ISubscribeToEvents<?> subscriber);
}
