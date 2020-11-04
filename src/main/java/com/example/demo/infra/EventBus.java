package com.example.demo.infra;

import com.example.demo.INotifiyDomainEvent;
import com.example.demo.IPublishEvents;
import com.example.demo.IStreamEvent;
import com.example.demo.ISubscribeToEvents;

import java.util.ArrayList;
import java.util.List;

public class EventBus implements IPublishEvents {
  private IStreamEvent eventStream;
  private List<ISubscribeToEvents> subscribers = new ArrayList<>();

  public EventBus(IStreamEvent inMemoryEventStream) {
    eventStream = inMemoryEventStream;
  }

  @Override
  public void publish(INotifiyDomainEvent event) {
    eventStream.add(event);
    subscribers.forEach(subscriber -> subscriber.handle(event));
  }

  public void subscribe(ISubscribeToEvents subscriber) {
    subscribers.add(subscriber);
  }
}
