package com.example.demo.rightside;

import com.example.demo.domain.EventPublisher;
import com.example.demo.domain.Message;
import com.example.demo.domain.MessageDeleted;
import com.example.demo.domain.MessageQuacked;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryEventStoreShould {

    @Test
    void containsMessageQuacked_whenQuackMessage() {
        // GIVEN
        Message message = new Message(1);
        EventPublisher eventPublisher = new EventPublisher();
        InMemoryEventStore inMemoryEventStore = new InMemoryEventStore();
        eventPublisher.subscribe(inMemoryEventStore);

        // WHEN
        message.quack(eventPublisher, "hello");

        // THEN
        assertThat(inMemoryEventStore.getPastEvents(1).getEvents())
                .containsExactly(new MessageQuacked(1, "hello"));
    }

    @Test
    void containsMessageQuacked_andMessageDeleted_whenQuackMessage_andDeleteMessage() {
        // GIVEN
        EventPublisher eventPublisher = new EventPublisher();
        InMemoryEventStore inMemoryEventStore = new InMemoryEventStore();
        eventPublisher.subscribe(inMemoryEventStore);
        Message message1 = new Message(1);
        message1.quack(eventPublisher, "hello");

        // WHEN
        message1.delete(eventPublisher);

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(inMemoryEventStore.getPastEvents(1).getEvents())
                    .containsExactly(
                            new MessageQuacked(1, "hello"),
                            new MessageDeleted(1)
                    );
            softAssertions.assertThat(inMemoryEventStore.getPastEvents(2).getEvents()).isEmpty();
        });
    }
}