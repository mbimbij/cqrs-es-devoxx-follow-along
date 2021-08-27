package com.example.demo.leftside;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
class ApplicationRestControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void name() {
        webTestClient.post()
                .uri("/quack")
                .body(Mono.just("hello"), String.class)
                .exchange()
                .expectStatus()
                .isAccepted();
    }
}