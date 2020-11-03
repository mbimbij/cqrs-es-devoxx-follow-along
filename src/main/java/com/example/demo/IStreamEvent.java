package com.example.demo;

public interface IStreamEvent {
  void add(INotifiyDomainEvent event);

  Iterable<INotifiyDomainEvent> getEvents();
}
