package com.example.demo.leftside;

import com.example.demo.domain.EventPublisher;
import com.example.demo.domain.Message;
import com.example.demo.domain.Timeline;
import com.example.demo.domain.TimelineMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class ApplicationRestController {
    @Autowired
    private EventPublisher eventPublisher;
    @Autowired
    private Timeline timeline;

    @PostMapping("/quack")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Void> quack(@RequestBody String message) {
        log.info("received message to quack: {}", message);
        Message.quackPublic(eventPublisher, message);
        return Flux.empty();
    }

    @GetMapping(value = "/timeline", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<TimelineMessage> timeline() {
        return Flux.fromIterable(timeline.getMessages());
    }
}
