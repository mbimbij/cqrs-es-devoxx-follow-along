package com.example.demo.domain.withid;

import com.example.demo.domain.DomainEvent;
import lombok.Value;

@Value
public class MessageDeleted implements DomainEvent {
    private int messageId;

    public MessageDeleted(int messageId) {
        this.messageId = messageId;
    }
}
