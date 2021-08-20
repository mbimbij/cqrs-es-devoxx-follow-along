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
}
