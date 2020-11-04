package com.example.demo;

import com.example.demo.externalprojections.QuackCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuackCounterTest {

  private QuackCounter counter;

  @BeforeEach
  void setUp() {
    counter = new QuackCounter();
  }

  @Test
  void shouldIncrement_whenQuacked() {
    counter.handle(new MessageQuacked("hello"));

    assertThat(counter.getValue()).isEqualTo(1);
  }

  @Test
  void shouldDecrement_whenMessageDeleted() {
    counter.handle(new MessageQuacked("hello"));

    counter.handle(new MessageDeleted());

    assertThat(counter.getValue()).isEqualTo(0);
  }
}
