package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // GIVEN
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();

        // WHEN
        Message.quack(eventPublisher, "hello");

        // THEN
        assertThat(eventPublisher.getEvents()).containsExactly(new MessageQuacked("hello"));
    }

}
