package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raise_message_quacked_when_quack_message() {
        List<IDomainEvent> history = new ArrayList<>();
        Message.quack(history, "Hello");
        assertThat(history).containsExactly(new MessageQuacked("Hello"));
    }

    @Test
    void raise_message_deleted_when_delete_message() {
        List<IDomainEvent> history = new ArrayList<>();
        history.add(new MessageQuacked("Hello"));
        Message message = new Message(history);

        message.delete(history);

        assertThat(history).containsExactly(new MessageQuacked("Hello"),
                new MessageDeleted());
    }

    @Test
    void not_raise_message_deleted_when_delete_deleted_message() {
        List<IDomainEvent> history = new ArrayList<>();
        history.add(new MessageQuacked("Hello"));
        history.add(new MessageDeleted());
        Message message = new Message(history);

        message.delete(history);

        assertThat(history).containsExactly(
                new MessageQuacked("Hello"),
                new MessageDeleted());
    }

    @Test
    void not_raise_message_deleted_a_second_time_when_delete_twice() {
        List<IDomainEvent> history = new ArrayList<>();
        history.add(new MessageQuacked("Hello"));
        Message message = new Message(history);

        message.delete(history);
        message.delete(history);

        assertThat(history).containsExactly(
                new MessageQuacked("Hello"),
                new MessageDeleted());
    }
}
