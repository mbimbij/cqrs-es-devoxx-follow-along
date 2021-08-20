package com.example.demo;

import lombok.Value;

@Value
public class TimelineMessage {
    private String content;

    public TimelineMessage(String content) {
        this.content = content;
    }
}
