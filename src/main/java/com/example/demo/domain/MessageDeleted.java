package com.example.demo.domain;

import com.example.demo.domain.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class MessageDeleted extends DomainEvent {
    public MessageDeleted(int messageId) {
        this.messageId = messageId;
    }
}
