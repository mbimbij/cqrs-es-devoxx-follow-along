package com.example.demo.domain;

import com.example.demo.domain.withid.MessageDeleted;

public class IsMessageDeletedProjection {
    private boolean isDeleted = false;

    public void apply(PublicMessageDeleted publicMessageDeleted) {
        isDeleted = true;
    }

    public void apply(MessageDeleted messageDeleted) {
        isDeleted = true;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
