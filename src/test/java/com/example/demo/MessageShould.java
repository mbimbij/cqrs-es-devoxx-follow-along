package com.example.demo;

import org.junit.jupiter.api.Test;

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
        InMemoryEventStream eventPublisher = new InMemoryEventStream();
        Message message = new Message();

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof MessageDeleted).hasSize(1);
    }
}
