package com.example.demo.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuackCounter {
    private final int messageId;
    private int value;

    public void handle(MessageQuacked publicMessageQuacked) {
        value++;
    }

    public int getValue() {
        return value;
    }

    public void handle(MessageDeleted publicMessageDeleted) {
        value--;
    }
}
