package com.example.demo;

import lombok.Value;

@Value
public class MessageQuacked implements IDomainEvent {
  private String content;
}
