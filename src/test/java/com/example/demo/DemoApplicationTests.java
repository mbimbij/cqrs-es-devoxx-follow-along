package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class DemoApplicationTests {

  @Test
  void messageShouldRaiseMessageQuack_whenQuackMessage() {
    var history = new ArrayList<>();
    Message.quack(history, "hello");
    assertThat(history).containsExactly(new MessageQuacked("hello"));
  }

  @Test
  void raiseMessageDeleted_whenDelete() {
    var history = new ArrayList<>();
    history.add(new MessageQuacked("hello"));
    Message message = new Message(history);
    message.delete(history);
    assertThat(history).contains(new MessageDeleted() );
  }

  @Test
  void shouldNotRaiseMessageDeleted_whenMessageAlreadyDeleted() {
    var history = new ArrayList<>();
    history.add(new MessageQuacked("hello"));
    history.add(new MessageDeleted());

    var message = new Message(history);
    message.delete(history);
    assertThat(history).satisfies(objects -> assertThat(objects.stream().filter(o -> o instanceof MessageDeleted).count()).isEqualTo(1));
  }
}
