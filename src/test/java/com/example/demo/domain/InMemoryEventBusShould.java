package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class InMemoryEventBusShould {
//    @Test
//    void callEachHandler_whenPublish() {
//        // GIVEN
//        InMemoryEventPublisher eventBus = new InMemoryEventPublisher();
//        MessageBroadcastedSubscriber messageBroadcastedSubscriber1 = new MessageBroadcastedSubscriber();
//        MessageBroadcastedSubscriber messageBroadcastedSubscriber2 = new MessageBroadcastedSubscriber();
//        MessagePubliclyDeletedSubscriber messagePubliclyDeletedSubscriber = new MessagePubliclyDeletedSubscriber();
//        eventBus.subscribe(messageBroadcastedSubscriber1);
//        eventBus.subscribe(messageBroadcastedSubscriber2);
//        eventBus.subscribe(messagePubliclyDeletedSubscriber);
//
//        // WHEN
//        eventBus.publish(new MessageBroadcasted("hello"));
//        eventBus.publish(new MessageBroadcasted("hello2"));
//        eventBus.publish(new PublicMessageDeleted());
//
//        // THEN
//        assertSoftly(softAssertions -> {
//            softAssertions.assertThat(messageBroadcastedSubscriber1.getCountHandle()).isEqualTo(2);
//            softAssertions.assertThat(messageBroadcastedSubscriber2.getCountHandle()).isEqualTo(2);
//            softAssertions.assertThat(messagePubliclyDeletedSubscriber.getCountHandle()).isEqualTo(1);
//        });
//    }
}