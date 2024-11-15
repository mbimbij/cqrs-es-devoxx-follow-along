package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class EventBusShould {

    @Test
    void store_events_when_publish_events() {
        InMemoryEventStream stream = new InMemoryEventStream();
        EventBus eventBus = new EventBus(stream);

        eventBus.publish(new MessageQuacked("hello"));

        assertThat(stream.getEvents()).containsExactly(new MessageQuacked("hello"));
    }

    @Test
    void call_each_handler_when_publish_event() {
        // GIVEN
        EventBus eventBus = new EventBus(new InMemoryEventStream());
        EventSubscriber<MessageQuacked> subscriber1 = new EventSubscriber<>();
        EventSubscriber<MessageQuacked> subscriber2 = new EventSubscriber<>();
        EventSubscriber<MessageQuacked> subscriber3 = new EventSubscriber<>();
        eventBus.subscribe(subscriber1);
        eventBus.subscribe(subscriber2);
        eventBus.subscribe(subscriber3);

        // WHEN
        eventBus.publish(new MessageQuacked("hello"));

        // THEN
        assertThat(subscriber1.wasCalled()).isTrue();
        assertThat(subscriber2.wasCalled()).isTrue();
        assertThat(subscriber3.wasCalled()).isTrue();
    }
}
