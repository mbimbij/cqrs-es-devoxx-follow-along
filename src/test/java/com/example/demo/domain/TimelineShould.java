package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimelineShould {
    @Test
    void displayMessage_whenMessageQuacked() {
        // GIVEN
        Timeline timeline = new Timeline();

        // WHEN
        timeline.handle(new MessageQuacked("hello"));

        // THEN
        assertThat(timeline.getMessages()).contains(new TimelineMessage("hello"));
    }
}
