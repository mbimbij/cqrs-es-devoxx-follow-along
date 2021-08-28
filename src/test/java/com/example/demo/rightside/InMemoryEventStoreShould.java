package com.example.demo.rightside;

import com.example.demo.domain.EventPublisher;
import com.example.demo.domain.Message;
import com.example.demo.domain.MessageDeleted;
import com.example.demo.domain.PublicMessageQuacked;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryEventStoreShould {
    @Test
    void containsNoEvent_whenInitialized() {
        InMemoryEventStore inMemoryEventStore = new InMemoryEventStore();
        assertThat(inMemoryEventStore.getPastEvents().getEvents()).isEmpty();
    }

    @Test
    void containsMessageQuacked_whenQuackMessage() {
        // GIVEN
        EventPublisher eventPublisher = new EventPublisher();
        InMemoryEventStore inMemoryEventStore = new InMemoryEventStore();
        eventPublisher.subscribe(inMemoryEventStore);

        // WHEN
        Message.quackPublic(eventPublisher, "hello");

        // THEN
        assertThat(inMemoryEventStore.getPastEvents().getEvents())
                .containsExactly(new PublicMessageQuacked("hello"));
    }

    @Test
    void containsMessageQuacked_andMessageDeleted_whenQuackMessage_andDeleteMessage() {
        // GIVEN
        EventPublisher eventPublisher = new EventPublisher();
        InMemoryEventStore inMemoryEventStore = new InMemoryEventStore();
        eventPublisher.subscribe(inMemoryEventStore);
        Message.quackPublic(eventPublisher, "hello");
        Message message = new Message(inMemoryEventStore.getPastEvents());

        // WHEN
        message.delete(eventPublisher);

        // THEN
        assertThat(inMemoryEventStore.getPastEvents().getEvents())
                .containsExactly(
                        new PublicMessageQuacked("hello"),
                        new MessageDeleted()
                );
    }
}