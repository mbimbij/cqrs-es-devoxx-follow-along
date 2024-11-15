package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MixterShould {
    @Test
    void display_message_in_timeline_when_quack_message() {
        InMemoryEventStream eventStream = new InMemoryEventStream();
        EventBus eventBus = new EventBus(eventStream);
        Timeline timeline = new Timeline(MessageQuacked.class);
        eventBus.subscribe(timeline);
        Message.quack(eventBus, "hello");
        assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
    }
}
