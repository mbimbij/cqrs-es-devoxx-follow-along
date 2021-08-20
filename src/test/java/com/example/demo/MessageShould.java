package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    private final List<IDomainEvent> history = new ArrayList<>();

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // WHEN
        Message.quack(history, "hello");

        // THEN
        assertThat(history).containsExactly(new MessageQuacked("hello"));
    }

    @Test
    void raiseMessageDeleted_whenDeleteMessage() {
        // GIVEN
        history.add(new MessageQuacked("hello"));
        Message message = new Message(history);

        // WHEN
        message.delete(history);

        // THEN
        assertThat(history).anyMatch(o -> o instanceof MessageDeleted);
    }

    @Test
    void notRaiseMessageDeleted_whenMessageAlreadyDeleted() {
        // GIVEN
        history.add(new MessageQuacked("hello"));
        history.add(new MessageDeleted());
        Message message = new Message(history);

        // WHEN
        message.delete(history);

        // THEN
        assertThat(history).filteredOn(event -> event instanceof MessageDeleted).hasSize(1);
    }

    @Test
    void notRaiseMessageDeleted_whenCallDeleteMessageTwice() {
        // GIVEN
        history.add(new MessageQuacked("hello"));
        Message message = new Message(history);

        // WHEN
        message.delete(history);
        message.delete(history);

        // THEN
        assertThat(history).filteredOn(event -> event instanceof MessageDeleted).hasSize(1);
    }
}
