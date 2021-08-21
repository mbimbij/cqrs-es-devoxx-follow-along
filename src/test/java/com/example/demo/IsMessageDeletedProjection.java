package com.example.demo;

public class IsMessageDeletedProjection {
    private boolean isDeleted = false;

    public void apply(MessageDeleted pastEvent) {
        isDeleted = true;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
