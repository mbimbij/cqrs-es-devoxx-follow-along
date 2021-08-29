package com.example.demo.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class DomainEvent {
    protected int messageId;
}
