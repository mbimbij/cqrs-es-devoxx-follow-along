package com.example.demo;

public class EventSubscriber<T extends INotifiyDomainEvent> implements ISubscribeToEvents {
  private boolean called;

  public boolean isCalled() {
    return called;
  }

  @Override
  public void handle(INotifiyDomainEvent event) {
    called = true;
  }
}
