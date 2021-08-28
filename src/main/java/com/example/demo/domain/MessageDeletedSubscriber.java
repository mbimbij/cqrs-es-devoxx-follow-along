package com.example.demo.domain;

public class MessageDeletedSubscriber implements ISubscribeToEvents<PublicMessageDeleted> {
    private int countHandle = 0;

    @Override
    public void handle(PublicMessageDeleted event) {
        countHandle++;
    }

    @Override
    public boolean accept(DomainEvent event) {
        return event instanceof PublicMessageDeleted;
    }


    public int getCountHandle() {
        return countHandle;
    }
}
