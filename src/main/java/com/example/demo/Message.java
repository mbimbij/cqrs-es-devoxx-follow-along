package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Message {
  private boolean isDeleted = false;

  public Message(List<IDomainEvent> history) {
    history.stream().forEach(this::apply);
    isDeleted = history.stream().anyMatch(o -> o instanceof MessageDeleted);
  }

  public static void quack(ArrayList<IDomainEvent> history, String content) {
    history.add(new MessageQuacked(content));
  }

  public void delete(ArrayList<IDomainEvent> history) {
    if(isDeleted){
      return;
    }
    MessageDeleted messageDeleted = new MessageDeleted();
    apply(messageDeleted);
    history.add(messageDeleted);
  }

  private void apply(IDomainEvent messageDeleted) {
    isDeleted = true;
  }
}
