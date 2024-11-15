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

    public void handle(IDomainEvent event) {
        switch (event){
            case MessageDeleted messageDeleted -> handle(messageDeleted);
            case MessageQuacked messageQuacked -> handle(messageQuacked);
        }
    }

    private void handle(MessageDeleted messageDeleted) {
        value--;
    }

    private void handle(MessageQuacked messageQuacked) {
        value++;
    }
}
