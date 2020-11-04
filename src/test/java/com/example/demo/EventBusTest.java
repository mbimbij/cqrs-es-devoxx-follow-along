package com.example.demo;

import com.example.demo.infra.EventBus;
import com.example.demo.infra.InMemoryEventStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventBusTest {

  private EventBus eventBus;
  private IStreamEvent stream;

  @BeforeEach
  void setUp() {
    stream = new InMemoryEventStream();
    eventBus = new EventBus(stream);
  }

  @Test
  void shouldStoreEvents_whenPublishEvents() {
    eventBus.publish(new MessageQuacked("hello"));

    assertThat(stream.getEvents()).containsExactly(new MessageQuacked("hello"));
  }

  @Test
  void shouldCallEachHandler_whenPublishEvent() {
    EventSubscriber<MessageQuacked> subscriber1 = new EventSubscriber<>();
    EventSubscriber<MessageQuacked> subscriber2 = new EventSubscriber<>();
    EventSubscriber<MessageQuacked> subscriber3 = new EventSubscriber<>();
    eventBus.subscribe(subscriber1);
    eventBus.subscribe(subscriber2);
    eventBus.subscribe(subscriber3);

    eventBus.publish(new MessageQuacked("hello"));
    assertThat(subscriber1.isCalled()).isTrue();
    assertThat(subscriber2.isCalled()).isTrue();
    assertThat(subscriber3.isCalled()).isTrue();
  }
}
