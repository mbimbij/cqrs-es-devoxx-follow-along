package com.example.demo.domain;

public class MessageQuackedSubscriber implements ISubscribeToEvents<MessageQuacked> {
    private int countHandle = 0;

    @Override
    public void handle(MessageQuacked event) {
        countHandle++;
    }

    @Override
    public boolean accept(DomainEvent event) {
        return event instanceof MessageQuacked;
    }


    public int getCountHandle() {
        return countHandle;
    }
}
