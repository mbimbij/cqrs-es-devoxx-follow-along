package com.example.demo;

import com.example.demo.infra.EventBus;
import com.example.demo.infra.InMemoryEventStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

  private IPublishEvents eventPublisher;
  private IStreamEvent eventStream;
  private Message message;

  @BeforeEach
  void setUp() {
    eventStream = new InMemoryEventStream();
    eventPublisher = new EventBus(eventStream);
    message = new Message(eventStream, eventPublisher);
  }

  @Test
  void shouldRaiseMessageQuack_whenQuackMessage() {
    message.quack("hello");
    assertThat(eventStream.getEvents()).containsExactly(new MessageQuacked("hello"));
  }

  @Test
  void shouldRaiseMessageDeleted_whenDelete() {
    eventPublisher.publish(new MessageQuacked("hello"));

    message.delete();

    assertThat(eventStream.getEvents()).contains(new MessageDeleted());
  }

  private long countMessageDeleted(IStreamEvent streamEvent) {
    return StreamSupport.stream(streamEvent.getEvents().spliterator(), false).filter(o -> o instanceof MessageDeleted).count();
  }

  @Test
  void shouldNotRaiseMessageDeletedEvent_whenDeleteTwice() {
    message.quack("hello");

    message.delete();
    message.delete();

    assertThat(eventStream).satisfies(objects -> assertThat(countMessageDeleted(objects)).isEqualTo(1));
  }
}
