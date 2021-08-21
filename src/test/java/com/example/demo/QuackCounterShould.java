package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuackCounterShould {
    @Test
    void increment_whenMessageQuacked() {
        // GIVEN
        QuackCounter quackCounter = new QuackCounter();

        // WHEN
        quackCounter.handle(new MessageQuacked("hello"));

        // THEN
        assertThat(quackCounter.getValue()).isEqualTo(1);
    }
}
