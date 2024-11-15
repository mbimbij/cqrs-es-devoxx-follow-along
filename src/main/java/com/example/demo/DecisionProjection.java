package com.example.demo;

public class DecisionProjection {
    boolean isDeleted = false;

    public DecisionProjection() {
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    void apply(IDomainEvent event) {
        if (event instanceof MessageDeleted) {
            setDeleted(true);
        }
    }
}
