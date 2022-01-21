package com.example.demo.leftside;

import com.example.demo.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ApplicationRestController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EventPublisher eventPublisher;
    @Autowired
    private Timelines timelines;

    @PostMapping("/quack/{messageId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Void> quack(@PathVariable("messageId") int messageId, @RequestBody String content) {
        log.info("received message to quack for message {}: {}", messageId, content);
        messageRepository.getMessageById(messageId)
                .orElse(new Message(messageId))
                .quack(eventPublisher, content);
        return Flux.empty();
    }

    @GetMapping(value = "/timeline/{messageId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<TimelineMessage> timeline(@PathVariable("messageId") int messageId) {
        return timelines.getTimeline(messageId)
                .map(Timeline::getMessages)
                .map(Flux::fromIterable)
                .orElse(Flux.empty());
    }
}
