package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MixterShould {
    @Test
    void displayMessageInTimeline_whenQuackMessage() {
        // GIVEN
        IPublishEvent eventPublisher = new EventBus(new InMemoryEventStream());
        Timeline timeline = new Timeline();
        eventPublisher.subscribe(timeline);

        // WHEN
        Message.quack(eventPublisher, "hello");

        // THEN
        assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
    }
}
