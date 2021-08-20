package com.example.demo;

public class QuackCounter {
    private int value;

    public void handle(MessageQuacked hello) {
        value++;
    }

    public int getValue() {
        return value;
    }
}
