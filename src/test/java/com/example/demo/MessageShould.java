package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raise_message_quacked_when_quack_message() {
        InMemoryEventStream history = new InMemoryEventStream();
        EventBus eventBus = new EventBus(history);
        Message.quack(eventBus, "Hello");
        assertThat(history.getEvents())
                .containsExactly(new MessageQuacked("Hello"));
    }

    @Test
    void raise_message_deleted_when_delete_message() {
        List<IDomainEvent> history = new ArrayList<>();
        history.add(new MessageQuacked("Hello"));
        Message message = new Message(history);

        InMemoryEventStream newHistory = new InMemoryEventStream();
        newHistory.add(new MessageQuacked("Hello"));

        message.delete(newHistory);

        assertThat(newHistory.getEvents()).containsExactly(new MessageQuacked("Hello"),
                new MessageDeleted());
    }

    @Test
    void not_raise_message_deleted_when_delete_deleted_message() {
        List<IDomainEvent> history = new ArrayList<>();
        history.add(new MessageQuacked("Hello"));
        history.add(new MessageDeleted());
        Message message = new Message(history);

        InMemoryEventStream newHistory = new InMemoryEventStream();
        newHistory.add(new MessageQuacked("Hello"));
        newHistory.add(new MessageDeleted());

        message.delete(newHistory);

        assertThat(newHistory.getEvents()).containsExactly(
                new MessageQuacked("Hello"),
                new MessageDeleted());
    }

    @Test
    void not_raise_message_deleted_a_second_time_when_delete_twice() {
        List<IDomainEvent> history = new ArrayList<>();
        history.add(new MessageQuacked("Hello"));
        Message message = new Message(history);

        InMemoryEventStream newHistory = new InMemoryEventStream();
        newHistory.add(new MessageQuacked("Hello"));

        message.delete(newHistory);
        message.delete(newHistory);

        assertThat(newHistory.getEvents()).containsExactly(
                new MessageQuacked("Hello"),
                new MessageDeleted());
    }
}
