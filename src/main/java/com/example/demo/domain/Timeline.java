package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Timeline implements ISubscribeToEvents<PublicMessageQuacked> {
    private List<TimelineMessage> messages = new ArrayList<>();

    @Override
    public void handle(PublicMessageQuacked publicMessageQuacked) {
        messages.add(new TimelineMessage(publicMessageQuacked.getContent()));
    }

    @Override
    public boolean accept(DomainEvent event) {
        return event instanceof PublicMessageQuacked;
    }

    public Collection<TimelineMessage> getMessages() {
        return messages;
    }

}
