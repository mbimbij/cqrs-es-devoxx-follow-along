package com.example.demo;

public class Message {
  private MessageIsDeletedProjection isDeletedProjection;
  private MessageContentProjection messageContentProjection;
  private IPublishEvents eventPublisher;

  public Message(IStreamEvent eventStream, IPublishEvents eventPublisher) {
    this.eventPublisher = eventPublisher;
    this.isDeletedProjection = new MessageIsDeletedProjection();
    this.messageContentProjection = new MessageContentProjection();
    eventStream.getEvents().forEach(event -> {
      this.isDeletedProjection.apply(event);
      this.messageContentProjection.apply(event);
    });
  }

  public void quack(String content) {
    MessageQuacked event = new MessageQuacked(content);
    publishAndApply(event);
  }

  private void publishAndApply(MessageQuacked event) {
    eventPublisher.publish(event);
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
    eventPublisher.publish(messageDeleted);
    isDeletedProjection.apply(messageDeleted);
  }

}
