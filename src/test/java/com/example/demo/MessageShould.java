package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // GIVEN
        InMemoryEventStream eventPublisher = new InMemoryEventStream();

        // WHEN
        Message.quack(eventPublisher, "hello");

        // THEN
        assertThat(eventPublisher.getEvents()).containsExactly(new MessageQuacked("hello"));
    }

    @Test
    void raiseMessageDeleted_whenDeleteMessage() {
        // GIVEN
        List<DomainEvent> events = new ArrayList<>();
        events.add(new MessageQuacked("hello"));
        InMemoryEventStream eventPublisher = new InMemoryEventStream(events);
        Message message = new Message(events);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof MessageDeleted).hasSize(1);
    }

    @Test
    void notRaiseMessageDelete_whenMessageAlreadyDeleted() {
        // GIVEN
        List<DomainEvent> events = new ArrayList<>();
        events.add(new MessageQuacked("hello"));
        events.add(new MessageDeleted());
        InMemoryEventStream eventPublisher = new InMemoryEventStream(events);
        Message message = new Message(events);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof MessageDeleted).hasSize(1);
    }
}
