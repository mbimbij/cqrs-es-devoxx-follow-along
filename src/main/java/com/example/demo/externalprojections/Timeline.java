package com.example.demo.externalprojections;

import com.example.demo.INotifiyDomainEvent;
import com.example.demo.ISubscribeToEvents;
import com.example.demo.MessageQuacked;

import java.util.ArrayList;
import java.util.List;

public class Timeline implements ISubscribeToEvents {
  private List<TimelineMessage> messages = new ArrayList<>();

  @Override
  public void handle(INotifiyDomainEvent event) {
    if (event instanceof MessageQuacked){
      String content = ((MessageQuacked) event).getContent();
      TimelineMessage timelineMessage = new TimelineMessage(content);
      messages.add(timelineMessage);
    }
  }

  public Iterable<TimelineMessage> getMessages() {
    return messages;
  }
}
