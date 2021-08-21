package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void callEachHandler_whenPublish() {
        // GIVEN
        IPublishEvent eventPublisher = new EventBus(new InMemoryStreamEvents());
        MessageQuackedSubscriber messageQuackedEventSubscriber1 = spy(new MessageQuackedSubscriber());
        MessageQuackedSubscriber messageQuackedEventSubscriber2 = spy(new MessageQuackedSubscriber());
        MessageDeletedSubscriber messageDeletedEventSubscriber = spy(new MessageDeletedSubscriber());
        eventPublisher.subscribe(messageQuackedEventSubscriber1);
        eventPublisher.subscribe(messageQuackedEventSubscriber2);
        eventPublisher.subscribe(messageDeletedEventSubscriber);

        // WHEN
        eventPublisher.publish(new MessageQuacked("hello"));

        // THEN
        verify(messageQuackedEventSubscriber1).handle(any(MessageQuacked.class));
        verify(messageQuackedEventSubscriber2).handle(any(MessageQuacked.class));
        verify(messageDeletedEventSubscriber, never()).handle(any(MessageDeleted.class));
    }
}
