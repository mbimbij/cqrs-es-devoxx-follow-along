package com.example.demo;

public class MessageDeletedSubscriber implements IEventSubscriber<MessageDeleted> {
    @Override
    public void handle(MessageDeleted event) {

    }

    @Override
    public boolean accepts(Class<?> aClass) {
        return aClass.equals(MessageDeleted.class);
    }
}
