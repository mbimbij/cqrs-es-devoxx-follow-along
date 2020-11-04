package com.example.demo;

import com.example.demo.externalprojections.Timeline;
import com.example.demo.externalprojections.TimelineMessage;
import com.example.demo.infra.EventBus;
import com.example.demo.infra.InMemoryEventStream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MixterTest {
  @Test
  void shouldDisplayMessageInTimeline_whenQuackMessage() {
    InMemoryEventStream eventStream = new InMemoryEventStream();
    EventBus eventBus = new EventBus(eventStream);
    Timeline timeline = new Timeline();
    eventBus.subscribe(timeline);
    Message message = new Message(eventStream, eventBus);

    message.quack("hello");

    assertThat(timeline.getMessages()).containsExactly(new TimelineMessage("hello"));
  }
}
