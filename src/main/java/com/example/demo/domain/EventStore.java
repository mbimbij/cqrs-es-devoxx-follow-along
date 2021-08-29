package com.example.demo.domain;

public interface EventStore extends ISubscribeToEvents<DomainEvent> {
    AggregatePastEvents getPastEvents(int messageId);
}
