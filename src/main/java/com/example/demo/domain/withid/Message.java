package com.example.demo.domain.withid;

import com.example.demo.domain.*;

public class Message {
    private int id;
    private IsMessageDeletedProjection deletionProjection = new IsMessageDeletedProjection();

    public Message(AggregatePastEvents pastAggregatePastEvents) {
        pastAggregatePastEvents.getEvents().forEach(pastEvent -> {
            if (pastEvent instanceof PublicMessageDeleted) {
                deletionProjection.apply((PublicMessageDeleted) pastEvent);
            }
        });
    }

    public Message(int id, AggregatePastEvents pastAggregatePastEvents) {
        this.id = id;
        pastAggregatePastEvents.getEvents().forEach(pastEvent -> {
            if (pastEvent instanceof PublicMessageDeleted) {
                deletionProjection.apply((PublicMessageDeleted) pastEvent);
            }
        });
    }

    public Message(int id) {
        this.id = id;
    }

    public static void quackPublic(EventPublisher eventPublisher, String content) {
        eventPublisher.publish(new PublicMessageQuacked(content));
    }

    public void deletePublic(EventPublisher eventPublisher) {
        if (!deletionProjection.isDeleted()) {
            PublicMessageDeleted event = new PublicMessageDeleted();
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

    public void delete(EventPublisher eventPublisher) {
        if (!deletionProjection.isDeleted()) {
            MessageDeleted event = new MessageDeleted(id);
            eventPublisher.publish(event);
            deletionProjection.apply(event);
        }
    }
}
