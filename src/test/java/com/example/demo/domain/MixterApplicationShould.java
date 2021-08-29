package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class MixterApplicationShould {
    @Test
    void displayMessageInTimeline_whenQuackMessage() {
        // GIVEN
        InMemoryEventPublisher eventBus = new InMemoryEventPublisher();
        Message message = new Message(1);
        Timeline timeline = new Timeline(1);
        Message message2 = new Message(2);
        Timeline timeline2 = new Timeline(2);
        eventBus.subscribe(timeline);
        eventBus.subscribe(timeline2);

        // WHEN
        message.quack(eventBus, "hello");

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
            softAssertions.assertThat(timeline2.getMessages()).isEmpty();
        });
    }
}
