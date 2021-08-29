package com.example.demo.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Timelines implements ISubscribeToEvents<MessageQuacked> {
    private Map<Integer, Timeline> timelines = new HashMap<>();

    @Override
    public void handle(MessageQuacked messageQuacked) {
        int messageId = messageQuacked.getMessageId();
        Timeline timeline = new Timeline(messageId);
        timeline.handle(messageQuacked);
        timelines.putIfAbsent(messageId, timeline);
    }

    @Override
    public boolean accept(DomainEvent event) {
        return event instanceof MessageQuacked;
    }

    public Optional<Timeline> getTimeline(int messageId) {
        return Optional.ofNullable(timelines.get(messageId));
    }

}
