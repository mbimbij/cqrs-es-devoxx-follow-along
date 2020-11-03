package com.example.demo;

public class Message {
  private final IStreamEvent history;
  private boolean isDeleted = false;
  private String content = null;

  public Message(IStreamEvent history) {
    this.history = history;
    this.history.getEvents().forEach(this::apply);
  }

  public void quack(String content) {
    history.add(new MessageQuacked(content));
  }

  public void delete() {
    if(isDeleted){
      return;
    }
    MessageDeleted messageDeleted = new MessageDeleted();
    apply(messageDeleted);
    history.add(messageDeleted);
  }

  private void apply(INotifiyDomainEvent event) {
    if(event instanceof MessageDeleted){
      isDeleted = true;
    }else if(event instanceof MessageQuacked){
      content = ((MessageQuacked) event).getContent();
    }
  }
}
