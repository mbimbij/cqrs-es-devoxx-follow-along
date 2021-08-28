package com.example.demo.domain;

import lombok.Value;

@Value
public class MessageQuacked implements DomainEvent {
    int messageId;
    String content;
}
