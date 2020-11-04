package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

  private InMemoryEventStream history;
  private Message message;

  @BeforeEach
  void setUp() {
    history = new InMemoryEventStream();
    message = new Message(history);
  }

  @Test
  void shouldRaiseMessageQuack_whenQuackMessage() {
    message.quack("hello");
    assertThat(history.getEvents()).containsExactly(new MessageQuacked("hello"));
  }

  @Test
  void shouldRaiseMessageDeleted_whenDelete() {
    history.add(new MessageQuacked("hello"));

    message.delete();

    assertThat(history.getEvents()).contains(new MessageDeleted());
  }

  @Test
  void shouldNotRaiseMessageDeleted_whenMessageAlreadyDeleted() {
    var history = new InMemoryEventStream();
    history.add(new MessageDeleted());
    var message = new Message(history);
    history.add(new MessageQuacked("hello"));

    message.delete();

    assertThat(history).satisfies(history1 -> assertThat(countMessageDeleted(history1)).isEqualTo(1));
  }

  private long countMessageDeleted(IStreamEvent streamEvent) {
    return StreamSupport.stream(streamEvent.getEvents().spliterator(),false).filter(o -> o instanceof MessageDeleted).count();
  }

  @Test
  void shouldNotRaiseMessageDeletedEvent_whenDeleteTwice() {
    history.add(new MessageQuacked("hello"));

    message.delete();
    message.delete();

    assertThat(history).satisfies(objects -> assertThat(countMessageDeleted(objects)).isEqualTo(1));
  }
}
