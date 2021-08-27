package com.example.demo.domain;

public interface IPublishEvent {
    void publish(DomainEvent domainEvent);

    void subscribe(ISubscribeToEvents<?> subscriber);
}
