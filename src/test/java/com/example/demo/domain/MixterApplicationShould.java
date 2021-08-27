package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MixterApplicationShould {
    @Test
    void displayMessageInTimeline_whenQuackMessage() {
        // GIVEN
        InMemoryEventPublisher eventBus = new InMemoryEventPublisher();
        Timeline timeline = new Timeline();
        eventBus.subscribe(timeline);

        // WHEN
        Message.quack(eventBus, "hello");

        // THEN
        assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
    }
}
