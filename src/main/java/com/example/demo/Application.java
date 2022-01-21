package com.example.demo;

import com.example.demo.domain.EventPublisher;
import com.example.demo.domain.EventStore;
import com.example.demo.domain.Timelines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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
