package com.example.demo.domain;

import com.example.demo.rightside.InMemoryEventStore;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class MessageRepositoryShould {
    @Test
    void returnEmpty_whenNoPastEvents() {
        // GIVEN
        InMemoryEventStore eventStore = new InMemoryEventStore();
        MessageRepository messageRepository = new MessageRepository(eventStore);

        // WHEN
        Optional<Message> messageOptional = messageRepository.getMessage();

        // THEN
        assertThat(messageOptional).isEmpty();
    }

    @Test
    void returnANotDeletedMessage_whenNoMessageDeletedEmitted() {
        // GIVEN
        InMemoryEventStore eventStore = new InMemoryEventStore();
        eventStore.handle(new PublicMessageQuacked("hello"));
        MessageRepository messageRepository = new MessageRepository(eventStore);

        // WHEN
        Optional<Message> messageOptional = messageRepository.getMessage();

        // THEN
        assertThat(messageOptional).isNotEmpty();
        assertThat(messageOptional.get().isDeleted()).isFalse();
    }

    @Test
    void returnADeletedMessage_whenAMessageDeletedWasEmitted() {
        // GIVEN
        InMemoryEventStore eventStore = new InMemoryEventStore();
        eventStore.handle(new PublicMessageQuacked("hello"));
        eventStore.handle(new MessageDeleted());
        MessageRepository messageRepository = new MessageRepository(eventStore);

        // WHEN
        Optional<Message> messageOptional = messageRepository.getMessage();

        // THEN
        assertThat(messageOptional).isNotEmpty();
        assertThat(messageOptional.get().isDeleted()).isTrue();
    }
}