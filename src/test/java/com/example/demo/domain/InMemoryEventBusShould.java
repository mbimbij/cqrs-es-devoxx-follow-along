package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class InMemoryEventBusShould {
    @Test
    void callEachHandler_whenPublish() {
        // GIVEN
        InMemoryEventPublisher eventBus = new InMemoryEventPublisher();
        MessageQuackedSubscriber messageQuackedSubscriber1 = new MessageQuackedSubscriber();
        MessageQuackedSubscriber messageQuackedSubscriber2 = new MessageQuackedSubscriber();
        MessageDeletedSubscriber messageDeletedSubscriber = new MessageDeletedSubscriber();
        eventBus.subscribe(messageQuackedSubscriber1);
        eventBus.subscribe(messageQuackedSubscriber2);
        eventBus.subscribe(messageDeletedSubscriber);

        // WHEN
        eventBus.publish(new PublicMessageQuacked("hello"));
        eventBus.publish(new PublicMessageQuacked("hello2"));
        eventBus.publish(new PublicMessageDeleted());

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(messageQuackedSubscriber1.getCountHandle()).isEqualTo(2);
            softAssertions.assertThat(messageQuackedSubscriber2.getCountHandle()).isEqualTo(2);
            softAssertions.assertThat(messageDeletedSubscriber.getCountHandle()).isEqualTo(1);
        });
    }
}