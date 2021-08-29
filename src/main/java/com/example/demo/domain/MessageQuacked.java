package com.example.demo.domain;

import com.example.demo.domain.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class MessageQuacked extends DomainEvent {
    String content;

    public MessageQuacked(int messageId, String content) {
        this.messageId = messageId;
        this.content = content;
    }
}
