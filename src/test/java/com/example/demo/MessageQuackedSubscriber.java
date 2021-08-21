package com.example.demo;

public class MessageQuackedSubscriber implements IEventSubscriber<MessageQuacked> {
    @Override
    public void handle(MessageQuacked event) {

    }

    @Override
    public boolean accepts(Class<?> aClass) {
        return aClass.equals(MessageQuacked.class);
    }
}
