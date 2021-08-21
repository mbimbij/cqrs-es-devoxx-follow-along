package com.example.demo;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class InMemoryEventBusShould {
    @Test
    void callEachHandler_whenPublish() {
        // GIVEN
        InMemoryEventBus eventBus = new InMemoryEventBus();
        MessageQuackedSubscriber messageQuackedSubscriber1 = new MessageQuackedSubscriber();
        MessageQuackedSubscriber messageQuackedSubscriber2 = new MessageQuackedSubscriber();
        MessageDeletedSubscriber messageDeletedSubscriber = new MessageDeletedSubscriber();
        eventBus.subscribe(messageQuackedSubscriber1);
        eventBus.subscribe(messageQuackedSubscriber2);
        eventBus.subscribe(messageDeletedSubscriber);

        // WHEN
        eventBus.publish(new MessageQuacked("hello"));
        eventBus.publish(new MessageQuacked("hello2"));
        eventBus.publish(new MessageDeleted());

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(messageQuackedSubscriber1.getCountHandle()).isEqualTo(2);
            softAssertions.assertThat(messageQuackedSubscriber2.getCountHandle()).isEqualTo(2);
            softAssertions.assertThat(messageDeletedSubscriber.getCountHandle()).isEqualTo(1);
        });
    }
}