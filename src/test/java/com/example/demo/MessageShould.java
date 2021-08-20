package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // GIVEN
        List<Object> history = new ArrayList<>();

        // WHEN
        Message.quack(history, "hello");

        // THEN
        assertThat(history).containsExactly(new MessageQuacked("hello"));
    }

    @Test
    void raiseMessageDeleted_whenDeleteMessage() {
        // GIVEN
        List<Object> history = new ArrayList<>();
        history.add(new MessageQuacked("hello"));
        Message message = new Message();

        // WHEN
        message.delete(history);

        // THEN
        assertThat(history).anyMatch(o -> o instanceof MessageDeleted);
    }
}
