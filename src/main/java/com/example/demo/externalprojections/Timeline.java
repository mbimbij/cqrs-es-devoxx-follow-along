package com.example.demo.externalprojections;

import com.example.demo.MessageQuacked;

import java.util.ArrayList;
import java.util.List;

public class Timeline {
  private List<TimelineMessage> messages = new ArrayList<>();

  public void handle(MessageQuacked message) {
    String content = message.getContent();
    TimelineMessage timelineMessage = new TimelineMessage(content);
    messages.add(timelineMessage);
  }

  public Iterable<TimelineMessage> getMessages() {
    return messages;
  }
}
