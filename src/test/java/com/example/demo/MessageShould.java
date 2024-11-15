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
        InMemoryEventStream eventStream = new InMemoryEventStream(history);
        EventBus eventBus = new EventBus(eventStream);
        Message message = new Message(eventStream);

        message.delete(eventBus);

        assertThat(eventStream.getEvents()).containsExactly(new MessageQuacked("Hello"),
                new MessageDeleted());
    }

    @Test
    void not_raise_message_deleted_when_delete_deleted_message() {
        InMemoryEventStream eventStream = new InMemoryEventStream();
        eventStream.add(new MessageQuacked("Hello"));
        eventStream.add(new MessageDeleted());
        Message message = new Message(eventStream);
        EventBus eventBus = new EventBus(eventStream);

        message.delete(eventBus);

        assertThat(eventStream.getEvents()).containsExactly(
                new MessageQuacked("Hello"),
                new MessageDeleted());
    }

    @Test
    void not_raise_message_deleted_a_second_time_when_delete_twice() {
        InMemoryEventStream eventStream = new InMemoryEventStream();
        eventStream.add(new MessageQuacked("Hello"));
        Message message = new Message(eventStream);
        EventBus eventBus = new EventBus(eventStream);

        message.delete(eventBus);
        message.delete(eventBus);

        assertThat(eventStream.getEvents()).containsExactly(
                new MessageQuacked("Hello"),
                new MessageDeleted());
    }
}
