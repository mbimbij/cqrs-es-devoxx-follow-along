package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageShould {

  @Test
  void raise_message_quacked_when_quack_message() {
    List<Object> history = new ArrayList<Object>();
    Message.quack(history, "Hello");
    assertThat(history).containsExactly(new MessageQuacked("Hello"));
  }

}
