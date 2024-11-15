package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuackCounterShould {
    @Test
    void increment_when_message_quacked() {
        var counter = new QuackCounter();

        counter.handle(new MessageQuacked("hello"));
        
        assertThat(counter.getValue()).isEqualTo(1);
    }

    @Test
    void decrement_when_message_deleted() {
        List<IDomainEvent> history = List.of(new MessageQuacked("hello"));
        var counter = new QuackCounter(history);

        counter.handle(new MessageDeleted());

        assertThat(counter.getValue()).isEqualTo(0);
    }

    @Test
    void be_0_when_created_with_mesageQuacked_and_messageDeleted() {
        List<IDomainEvent> history = List.of(
                new MessageQuacked("hello"),
                new MessageDeleted()
        );
        var counter = new QuackCounter(history);

        assertThat(counter.getValue()).isEqualTo(0);
    }
}
