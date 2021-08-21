package com.example.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MixterShould {
    @Test
    @Disabled
    void displayMessageInTimeline_whenQuackMessage() {
        // GIVEN
        Timeline timeline = new Timeline();

        // WHEN
        Message.quack(new InMemoryStreamEvents(), "hello");

        // THEN
        assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
    }
}
