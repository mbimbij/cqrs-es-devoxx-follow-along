package com.example.demo.domain;

public class MessageDeletedSubscriber implements ISubscribeToEvents<MessageDeleted> {
    private int countHandle = 0;

    @Override
    public void handle(MessageDeleted event) {
        countHandle++;
    }

    @Override
    public boolean accept(DomainEvent event) {
        return event instanceof MessageDeleted;
    }


    public int getCountHandle() {
        return countHandle;
    }
}
