package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // GIVEN
        InMemoryEventBus eventPublisher = new InMemoryEventBus();

        // WHEN
        Message.quack(eventPublisher, "hello");

        // THEN
        assertThat(eventPublisher.getEvents()).containsExactly(new MessageQuacked("hello"));
    }

    @Test
    void raiseMessageDeleted_whenDeleteMessage() {
        // GIVEN
        List<DomainEvent> eventsList = new ArrayList<>();
        eventsList.add(new MessageQuacked("hello"));
        InMemoryEventBus eventPublisher = new InMemoryEventBus(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof MessageDeleted).hasSize(1);
    }

    @Test
    void notRaiseMessageDelete_whenMessageAlreadyDeleted() {
        // GIVEN
        List<DomainEvent> eventsList = new ArrayList<>();
        eventsList.add(new MessageQuacked("hello"));
        eventsList.add(new MessageDeleted());
        InMemoryEventBus eventPublisher = new InMemoryEventBus(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof MessageDeleted).hasSize(1);
    }

    @Test
    void notRaiseMessageDeleted_whenCallDeleteMessageTwice() {
        // GIVEN
        List<DomainEvent> eventsList = new ArrayList<>();
        eventsList.add(new MessageQuacked("hello"));
        InMemoryEventBus eventPublisher = new InMemoryEventBus(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.delete(eventPublisher);
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof MessageDeleted).hasSize(1);
    }
}
