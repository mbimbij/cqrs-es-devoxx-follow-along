package com.example.demo;

public class Message {
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

    public static void quack(IPublishEvent eventPublisher, String content) {
        eventPublisher.publish(new MessageQuacked(content));
    }

    public void delete(IPublishEvent eventPublisher) {
        if (!deletionProjection.isDeleted()) {
            MessageDeleted event = new MessageDeleted();
            eventPublisher.publish(event);
            deletionProjection.apply(event);
        }
    }
}
