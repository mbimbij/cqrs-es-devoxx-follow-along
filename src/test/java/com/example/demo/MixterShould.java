package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MixterShould {
    @Test
    void display_message_in_timeline_when_quack_message() {
        Timeline timeline = new Timeline();
        Message.quack(new InMemoryEventStream(), "hello");
        assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
    }
}
