package com.example.demo.domain.withid;

import com.example.demo.domain.DomainEvent;
import lombok.Value;

@Value
public class MessageQuacked implements DomainEvent {
    int messageId;
    String content;
}
