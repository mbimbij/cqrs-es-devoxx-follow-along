package com.example.demo;

import com.example.demo.externalprojections.Projection;

public class MessageContentProjection implements Projection {
  private String content;

  @Override
  public void apply(INotifiyDomainEvent event) {
    if (event instanceof MessageQuacked){
      content = ((MessageQuacked) event).getContent();
    }
  }

  public String getContent() {
    return content;
  }
}
