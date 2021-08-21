package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MixterApplicationShould {
    @Test
    void displayMessageInTimeline_whenQuackMessage() {
        // GIVEN
        InMemoryEventBus eventBus = new InMemoryEventBus();
        Timeline timeline = new Timeline();
        eventBus.subscribe(timeline);

        // WHEN
        Message.quack(eventBus, "hello");

        // THEN
        assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
    }
}
