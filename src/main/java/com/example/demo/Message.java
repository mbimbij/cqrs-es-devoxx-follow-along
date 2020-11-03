package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Message {
  private boolean isDeleted = false;

  public Message(List<Object> history) {
    isDeleted = history.stream().anyMatch(o -> o instanceof MessageDeleted);
  }

  public static void quack(ArrayList<Object> history, String content) {
    history.add(new MessageQuacked(content));
  }

  public void delete(ArrayList<Object> history) {
    if(isDeleted){
      return;
    }
    history.add(new MessageDeleted());
    isDeleted = true;
  }
}
