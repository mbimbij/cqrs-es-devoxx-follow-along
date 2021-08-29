package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // GIVEN
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();
        int messageId = 1;
        Message message = new Message(messageId);

        // WHEN
        message.quack(eventPublisher, "hello");

        // THEN
        assertThat(eventPublisher.getEvents()).containsExactly(new MessageQuacked(messageId, "hello"));
    }

    @Test
    void raiseMessageDeleted_whenDeleteMessage() {
        // GIVEN
        List<DomainEvent> eventsList = new ArrayList<>();
        int messageId = 1;
        eventsList.add(new MessageQuacked(messageId, "hello"));
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(messageId, eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).contains(new MessageDeleted(messageId));
    }

    @Test
    void notRaiseMessageDelete_whenMessageAlreadyDeleted() {
        // GIVEN
        int messageId = 1;
        List<DomainEvent> eventsList = new ArrayList<>();
        eventsList.add(new MessageQuacked(messageId, "hello"));
        eventsList.add(new MessageDeleted(messageId));
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(messageId, eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).containsExactly(new MessageQuacked(messageId, "hello"), new MessageDeleted(1));
    }

    @Test
    void notRaiseMessageDeleted_whenCallDeleteMessageTwice() {
        // GIVEN
        int messageId = 1;
        List<DomainEvent> eventsList = new ArrayList<>();
        eventsList.add(new MessageQuacked(messageId, "hello"));
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(messageId, eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.delete(eventPublisher);
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof MessageDeleted).hasSize(1);
    }
}
