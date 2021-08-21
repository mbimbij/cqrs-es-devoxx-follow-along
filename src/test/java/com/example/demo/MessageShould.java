package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    private IStreamEvents history;
    private IPublishEvent eventPublisher;

    @BeforeEach
    void setUp() {
        history = new InMemoryStreamEvents();
        eventPublisher = new EventBus(history);
    }

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // WHEN
        Message.quack(eventPublisher, "hello");

        // THEN
        assertThat(history.getEvents()).containsExactly(new MessageQuacked("hello"));
    }

    @Test
    void raiseMessageDeleted_whenDeleteMessage() {
        // GIVEN
        history.add(new MessageQuacked("hello"));
        Message message = new Message(history);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(history.getEvents()).anyMatch(o -> o instanceof MessageDeleted);
    }

    @Test
    void notRaiseMessageDeleted_whenMessageAlreadyDeleted() {
        // GIVEN
        history.add(new MessageQuacked("hello"));
        history.add(new MessageDeleted());
        Message message = new Message(history);

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(history.getEvents()).filteredOn(event -> event instanceof MessageDeleted).hasSize(1);
    }

    @Test
    void notRaiseMessageDeleted_whenCallDeleteMessageTwice() {
        // GIVEN
        history.add(new MessageQuacked("hello"));
        Message message = new Message(history);

        // WHEN
        message.delete(eventPublisher);
        message.delete(eventPublisher);

        // THEN
        assertThat(history.getEvents()).filteredOn(event -> event instanceof MessageDeleted).hasSize(1);
    }
}
