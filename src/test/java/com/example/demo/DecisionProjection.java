package com.example.demo;

import lombok.Getter;

@Getter
public class DecisionProjection {
    private boolean isDeleted = false;

    public DecisionProjection(IStreamEvents history) {
        for (Object event : history.getEvents()) {
            if (event instanceof MessageDeleted) {
                apply((MessageDeleted) event);
            }
        }
    }

    public void apply(MessageDeleted messageDeleted) {
        isDeleted = true;
    }
}
