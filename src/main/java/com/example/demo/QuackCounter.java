package com.example.demo;

import lombok.Data;

import java.util.List;

@Data
public class QuackCounter {
    private int value;

    public QuackCounter() {
    }

    public QuackCounter(List<IDomainEvent> history) {
        history.forEach(this::handle);
    }

    private void handle(IDomainEvent event) {
        switch (event){
            case MessageDeleted messageDeleted -> handle(messageDeleted);
            case MessageQuacked messageQuacked -> handle(messageQuacked);
        }
    }

    public void handle(MessageDeleted messageDeleted) {
        value--;
    }

    public void handle(MessageQuacked messageQuacked) {
        value++;
    }
}
