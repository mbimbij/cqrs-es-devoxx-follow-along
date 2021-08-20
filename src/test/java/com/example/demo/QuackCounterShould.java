package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuackCounterShould {
    @Test
    void incrementWhenMessageQuacked() {
        QuackCounter counter = new QuackCounter();
        counter.handle(new MessageQuacked("hello"));

        assertThat(counter.getValue()).isEqualTo(1);
    }

    @Test
    void decrementWhenMessageDeleted() {
        // GIVEN
        QuackCounter counter = new QuackCounter();
        counter.handle(new MessageQuacked("hello"));

        // WHEN
        counter.handle(new MessageDeleted());

        // THEN
        assertThat(counter.getValue()).isEqualTo(0);
    }
}
