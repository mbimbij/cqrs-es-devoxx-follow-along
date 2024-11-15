package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventStream implements IEventStream {
    private final List<IDomainEvent> history = new ArrayList<>();

    @Override
    public void add(IDomainEvent event) {
        this.history.add(event);
    }

    public List<IDomainEvent> getEvents() {
        return history;
    }
}
