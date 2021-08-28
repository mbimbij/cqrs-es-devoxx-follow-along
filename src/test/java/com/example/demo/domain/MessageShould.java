package com.example.demo.domain;

import com.example.demo.domain.withid.Message;
import com.example.demo.domain.withid.MessageDeleted;
import com.example.demo.domain.withid.MessageQuacked;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

    @Test
    void raiseMessageQuacked_whenQuackMessage() {
        // GIVEN
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();

        // WHEN
        Message.quackPublic(eventPublisher, "hello");

        // THEN
        assertThat(eventPublisher.getEvents()).containsExactly(new PublicMessageQuacked("hello"));
    }

    @Test
    void notRaiseMessageDelete_whenMessageAlreadyDeleted() {
        // GIVEN
        List<DomainEvent> eventsList = new ArrayList<>();
        eventsList.add(new PublicMessageQuacked("hello"));
        eventsList.add(new PublicMessageDeleted());
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.deletePublic(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof PublicMessageDeleted).hasSize(1);
    }

    @Test
    void notRaiseMessageDeleted_whenCallDeleteMessageTwice() {
        // GIVEN
        List<DomainEvent> eventsList = new ArrayList<>();
        eventsList.add(new PublicMessageQuacked("hello"));
        InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher(eventsList);
        AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(eventsList);
        Message message = new Message(aggregatePastEvents);

        // WHEN
        message.deletePublic(eventPublisher);
        message.deletePublic(eventPublisher);

        // THEN
        assertThat(eventPublisher.getEvents()).filteredOn(domainEvent -> domainEvent instanceof PublicMessageDeleted).hasSize(1);
    }

    @Nested
    public class WithId {
        @Test
        void raiseMessageQuacked_whenQuackMessage() {
            // GIVEN
            InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();
            int messageId = 1;
            Message message = new Message(messageId);

            // WHEN
            message.quack(eventPublisher, "hello");

            // THEN
            assertThat(eventPublisher.getEvents()).containsExactly(new MessageQuacked(messageId, "hello"));
        }

        @Test
        void raiseMessageDeleted_whenDeleteMessage() {
            // GIVEN
            List<DomainEvent> eventsList = new ArrayList<>();
            int messageId = 1;
            eventsList.add(new MessageQuacked(messageId, "hello"));
            InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher(eventsList);
            AggregatePastEvents aggregatePastEvents = new AggregatePastEvents(eventsList);
            Message message = new Message(messageId, aggregatePastEvents);

            // WHEN
            message.delete(eventPublisher);

            // THEN
            assertThat(eventPublisher.getEvents()).contains(new MessageDeleted(messageId));
        }
    }
}
