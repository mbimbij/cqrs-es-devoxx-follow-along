package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventBusShould {
    @Test
    void storeEvents_whenPublishEvents() {
        // GIVEN
        IStreamEvents eventStream = new InMemoryStreamEvents();
        EventBus eventBus = new EventBus(eventStream);

        // WHEN
        eventBus.publish(new MessageQuacked("hello"));

        // THEN
        assertThat(eventStream.getEvents()).contains(new MessageQuacked("hello"));
    }
}
