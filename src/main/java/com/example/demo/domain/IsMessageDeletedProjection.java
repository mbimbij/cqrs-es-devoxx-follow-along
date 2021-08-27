package com.example.demo.domain;

public class IsMessageDeletedProjection {
    private boolean isDeleted = false;

    public void apply(MessageDeleted pastEvent) {
        isDeleted = true;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
