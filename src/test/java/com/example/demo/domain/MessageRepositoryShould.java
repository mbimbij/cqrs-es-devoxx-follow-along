package com.example.demo.domain;

import com.example.demo.rightside.InMemoryEventStore;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MessageRepositoryShould {
    @Test
    void returnEmpty_whenNoPastEvents() {
        // GIVEN
        InMemoryEventStore eventStore = new InMemoryEventStore();
        MessageRepository messageRepository = new MessageRepository(eventStore);

        // WHEN
        Optional<Message> messageOptional = messageRepository.getMessageById(1);

        // THEN
        assertThat(messageOptional).isEmpty();
    }

}