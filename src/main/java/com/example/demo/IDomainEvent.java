package com.example.demo;

public sealed interface IDomainEvent permits MessageQuacked, MessageDeleted {
}
