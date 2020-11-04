package com.example.demo;

public class Message {
  private final IStreamEvent history;
  private String content = null;
  private MessageIsDeletedProjection isDeletedProjection;
  private MessageContentProjection messageContentProjection;

  public Message(IStreamEvent history) {
    this.history = history;
    this.isDeletedProjection = new MessageIsDeletedProjection();
    this.messageContentProjection = new MessageContentProjection();
    history.getEvents().forEach(event -> {
      this.isDeletedProjection.apply(event);
      this.messageContentProjection.apply(event);
    });
  }

  public void quack(String content) {
    MessageQuacked event = new MessageQuacked(content);
    publishAndApply(event);
  }

  private void publishAndApply(MessageQuacked event) {
    history.add(event);
    messageContentProjection.apply(event);
  }

  public void delete() {
    if(isDeletedProjection.isDeleted()){
      return;
    }
    MessageDeleted messageDeleted = new MessageDeleted();
    publishAndApply(messageDeleted);
  }

  private void publishAndApply(MessageDeleted messageDeleted) {
    history.add(messageDeleted);
    isDeletedProjection.apply(messageDeleted);
  }

}
