package com.example.demo;

public class MessageDeletedSubscriber implements ISubscribeToEvents<MessageDeleted> {
    private int countHandle = 0;

    @Override
    public void handle(MessageDeleted event) {
        countHandle++;
    }

    @Override
    public boolean accept(Class<?> clazz) {
        return MessageDeleted.class.equals(clazz);
    }

    public int getCountHandle() {
        return countHandle;
    }
}
