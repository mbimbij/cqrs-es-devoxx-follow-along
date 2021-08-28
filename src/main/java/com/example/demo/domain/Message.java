package com.example.demo.domain;

public class Message {
    private int id;
    private IsMessageDeletedProjection deletionProjection = new IsMessageDeletedProjection();

    public Message() {
    }

    public Message(AggregatePastEvents pastAggregatePastEvents) {
        pastAggregatePastEvents.getEvents().forEach(pastEvent -> {
            if (pastEvent instanceof MessageDeleted) {
                deletionProjection.apply((MessageDeleted) pastEvent);
            }
        });
    }

    public Message(int id) {
        this.id = id;
    }

    public static void quackPublic(EventPublisher eventPublisher, String content) {
        eventPublisher.publish(new PublicMessageQuacked(content));
    }

    public void delete(EventPublisher eventPublisher) {
        if (!deletionProjection.isDeleted()) {
            MessageDeleted event = new MessageDeleted();
            eventPublisher.publish(event);
            deletionProjection.apply(event);
        }
    }

    public boolean isDeleted() {
        return deletionProjection.isDeleted();
    }

    public void quack(EventPublisher eventPublisher, String content) {
        eventPublisher.publish(new MessageQuacked(id, content));
    }
}
