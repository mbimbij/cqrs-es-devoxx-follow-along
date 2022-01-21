package com.example.demo.leftside;

import com.example.demo.ApplicationConfiguration;
import com.example.demo.domain.TimelineMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Import(ApplicationConfiguration.class)
@WebFluxTest
class ApplicationRestControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenQuack_thenMessageInTimeline() {
        // WHEN
        webTestClient.post()
                .uri("/quack/{id}", 1)
                .body(Mono.just("hello"), String.class)
                .exchange()
                .expectStatus()
                .isAccepted();

        // THEN
        Flux<TimelineMessage> timelineResponse = webTestClient.get()
                .uri("/timeline/{id}", 1)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(TimelineMessage.class)
                .getResponseBody();
        StepVerifier.create(timelineResponse)
                .expectNext(new TimelineMessage("hello"))
                .verifyComplete();

        Flux<TimelineMessage> timelineResponse2 = webTestClient.get()
                .uri("/timeline/{id}", 2)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(TimelineMessage.class)
                .getResponseBody();
        StepVerifier.create(timelineResponse2)
                .expectComplete()
                .verify();
    }
}
