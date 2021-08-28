package com.example.demo.leftside;

import com.example.demo.domain.TimelineMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@WebFluxTest
class ApplicationRestControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenQuack_thenMessageInTimeline() {
        // WHEN
        webTestClient.post()
                .uri("/quack")
                .body(Mono.just("hello"), String.class)
                .exchange()
                .expectStatus()
                .isAccepted();

        // THEN
        Flux<TimelineMessage> timelineResponse = webTestClient.get()
                .uri("/timeline")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(TimelineMessage.class)
                .getResponseBody();
        StepVerifier.create(timelineResponse)
                .expectNext(new TimelineMessage("hello"))
                .verifyComplete();
    }
}