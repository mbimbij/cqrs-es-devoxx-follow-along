package com.example.demo;

public interface IPublishEvent {
    <T extends DomainEvent> void publish(T event);

    void subscribe(IEventSubscriber<? extends DomainEvent> subscriber);
}
