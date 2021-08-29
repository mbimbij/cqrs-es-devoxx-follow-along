package com.example.demo.domain;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MessageRepository {

    private final EventStore eventStore;

    public Optional<Message> getMessageById(int messageId) {
        return Optional.of(eventStore.getPastEvents(messageId))
                .filter(aggregatePastEvents -> aggregatePastEvents.getEvents().iterator().hasNext())
                .map(Message::new);
    }
}
