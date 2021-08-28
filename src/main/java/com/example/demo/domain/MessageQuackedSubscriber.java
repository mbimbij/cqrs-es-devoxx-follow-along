package com.example.demo.domain;

public class MessageQuackedSubscriber implements ISubscribeToEvents<PublicMessageQuacked> {
    private int countHandle = 0;

    @Override
    public void handle(PublicMessageQuacked event) {
        countHandle++;
    }

    @Override
    public boolean accept(DomainEvent event) {
        return event instanceof PublicMessageQuacked;
    }


    public int getCountHandle() {
        return countHandle;
    }
}
