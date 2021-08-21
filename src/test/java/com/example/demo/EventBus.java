package com.example.demo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventBus {
    private final IStreamEvents eventStream;

    public void publish(DomainEvent event) {
        eventStream.add(event);
    }
}
