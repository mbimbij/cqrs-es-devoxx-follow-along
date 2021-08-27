package com.example.demo.domain;

public class QuackCounter {
    private int value;

    public void handle(MessageQuacked messageQuacked) {
        value++;
    }

    public int getValue() {
        return value;
    }

    public void handle(MessageDeleted messageDeleted) {
        value--;
    }
}
