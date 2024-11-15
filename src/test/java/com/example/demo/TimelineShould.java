package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimelineShould {
    @Test
    void display_message_when_message_quacked() {
        Timeline timeline = new Timeline(MessageQuacked.class);

        timeline.handle(new MessageQuacked("hello"));

        assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
    }

}
