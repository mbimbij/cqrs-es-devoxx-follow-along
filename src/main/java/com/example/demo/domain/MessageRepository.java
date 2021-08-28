package com.example.demo.domain;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MessageRepository {

    private final EventStore eventStore;

    Optional<Message> getMessage() {
        return Optional.of(eventStore.getPastEvents())
                .filter(aggregatePastEvents -> aggregatePastEvents.getEvents().iterator().hasNext())
                .map(Message::new);
    }
}
