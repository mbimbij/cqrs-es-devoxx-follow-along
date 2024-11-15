package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryEventStream implements IEventStream {
    private final List<IDomainEvent> history;

    public InMemoryEventStream() {
        this.history = new ArrayList<>();
    }

    public InMemoryEventStream(List<IDomainEvent> history) {
        this.history = history;
    }

    @Override
    public void add(IDomainEvent event) {
        this.history.add(event);
    }

    @Override
    public List<IDomainEvent> getEvents() {
        return Collections.unmodifiableList(history);
    }
}
