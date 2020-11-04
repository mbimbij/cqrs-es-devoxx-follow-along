package com.example.demo;

import com.example.demo.externalprojections.Timeline;
import com.example.demo.externalprojections.TimelineMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimelineTest {
  @Test
  void shouldDisplayMessage_whenMessageQuacked() {
    var timeline = new Timeline();

    timeline.handle(new MessageQuacked("hello"));

    assertThat(timeline.getMessages()).contains(new TimelineMessage("hello"));
  }
}
