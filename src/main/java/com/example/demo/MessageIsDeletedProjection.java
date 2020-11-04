package com.example.demo;

import com.example.demo.externalprojections.Projection;

public class MessageIsDeletedProjection implements Projection {
  private boolean isDeleted = false;
  @Override
  public void apply(INotifiyDomainEvent event) {
    if(event instanceof MessageDeleted){
      isDeleted = true;
    }
  }

  public boolean isDeleted() {
    return isDeleted;
  }
}
