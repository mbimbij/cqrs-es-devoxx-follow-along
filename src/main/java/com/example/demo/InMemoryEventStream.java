package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventStream implements IStreamEvent {
  private List<INotifiyDomainEvent> history = new ArrayList<>();

  @Override
  public void add(INotifiyDomainEvent event) {
    history.add(event);
  }

  @Override
  public Iterable<INotifiyDomainEvent> getEvents(){
    return history;
  }
}
