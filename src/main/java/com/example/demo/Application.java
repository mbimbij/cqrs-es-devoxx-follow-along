package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.rightside.InMemoryEventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EventPublisher eventPublisher() {
        return new EventPublisher();
    }

    @Bean
    public Timelines timeline() {
        return new Timelines();
    }

    @Bean
    public EventStore eventStore() {
        return new InMemoryEventStore();
    }

    @Bean
    public MessageRepository messageRepository(EventStore eventStore) {
        return new MessageRepository(eventStore);
    }

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private Timelines timelines;

    @Autowired
    private EventStore eventStore;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventPublisher.subscribe(timelines);
        eventPublisher.subscribe(eventStore);
    }
}
