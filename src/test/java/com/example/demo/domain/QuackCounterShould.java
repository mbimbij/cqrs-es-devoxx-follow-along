package com.example.demo.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class QuackCounterShould {
    @Test
    void increment_whenMessageQuacked() {
        // GIVEN
        QuackCounter quackCounter = new QuackCounter(1);
        QuackCounter quackCounter2 = new QuackCounter(2);

        // WHEN
        quackCounter.handle(new MessageQuacked(1, "hello"));

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(quackCounter.getValue()).isEqualTo(1);
            softAssertions.assertThat(quackCounter2.getValue()).isEqualTo(0);
        });
    }

    @Test
    void decrement_whenMessageDeleted() {
        // GIVEN
        QuackCounter quackCounter = new QuackCounter(1);
        QuackCounter quackCounter2 = new QuackCounter(2);
        quackCounter.handle(new MessageQuacked(1, "hello"));
        quackCounter.handle(new MessageQuacked(2, "world"));

        // WHEN
        quackCounter.handle(new MessageDeleted(1));

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(quackCounter.getValue()).isEqualTo(1);
            softAssertions.assertThat(quackCounter2.getValue()).isEqualTo(0);
        });
    }
}
