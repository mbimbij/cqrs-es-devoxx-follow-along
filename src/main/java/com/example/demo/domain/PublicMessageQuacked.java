package com.example.demo.domain;

import lombok.Value;

@Value
public class PublicMessageQuacked implements DomainEvent {
    String content;
}
