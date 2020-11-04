package com.example.demo.externalprojections;

import com.example.demo.MessageDeleted;
import com.example.demo.MessageQuacked;

public class QuackCounter {

  private int value = 0;

  public int getValue() {
    return value;
  }

  public void handle(MessageQuacked hello) {
    value++;
  }

  public void handle(MessageDeleted messageDeleted) {
    value--;
  }
}
