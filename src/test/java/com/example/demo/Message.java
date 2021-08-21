package com.example.demo;

public class Message {
    private boolean isDeleted = false;

    public Message() {
    }

    public Message(AggregatePastEvents pastAggregatePastEvents) {
        pastAggregatePastEvents.getEvents().forEach(pastEvent -> {
            if (pastEvent instanceof MessageDeleted) {
                apply((MessageDeleted) pastEvent);
            }
        });
    }

    private void apply(MessageDeleted pastEvent) {
        isDeleted = true;
    }

    public static void quack(IPublishEvent eventPublisher, String content) {
        eventPublisher.publish(new MessageQuacked(content));
    }

    public void delete(IPublishEvent eventPublisher) {
        if (!isDeleted) {
            MessageDeleted event = new MessageDeleted();
            eventPublisher.publish(event);
            apply(event);
        }
    }
}
