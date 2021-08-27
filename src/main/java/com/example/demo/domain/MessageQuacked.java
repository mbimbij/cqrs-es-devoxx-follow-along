package com.example.demo.domain;

import lombok.Value;

@Value
public class MessageQuacked implements DomainEvent {
    String content;
}
