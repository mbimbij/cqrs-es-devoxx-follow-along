package com.example.demo;

import lombok.Value;

@Value
public class MessageQuacked implements IDomainEvent {
    String content;
}
