package com.example.demo;

import java.util.List;

public interface IEventStream {
    void add(IDomainEvent event);

    List<IDomainEvent> getEvents();
}
