package com.example.demo.domain;

public class Message {
    private int id;
    private IsMessageDeletedProjection deletionProjection = new IsMessageDeletedProjection();

    public Message(AggregatePastEvents aggregatePastEvents) {
        this.id = aggregatePastEvents.getMessageId();
        aggregatePastEvents.getEvents().forEach(pastEvent -> {
            if (pastEvent instanceof MessageDeleted) {
                deletionProjection.apply((MessageDeleted) pastEvent);
            }
        });
    }

    public Message(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deletionProjection.isDeleted();
    }

    public void quack(EventPublisher eventPublisher, String content) {
        eventPublisher.publish(new MessageQuacked(id, content));
    }

    public void delete(EventPublisher eventPublisher) {
        if (!deletionProjection.isDeleted()) {
            MessageDeleted event = new MessageDeleted(id);
            eventPublisher.publish(event);
            deletionProjection.apply(event);
        }
    }
}
