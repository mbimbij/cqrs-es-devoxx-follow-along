package com.example.demo.leftside;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class ApplicationRestController {
    @PostMapping("/quack")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Void> quack(@RequestBody String message) {
        log.info("received message to quack: {}", message);
        return Flux.empty();
    }
}
