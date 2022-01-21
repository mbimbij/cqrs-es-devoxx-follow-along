package com.example.demo;

import com.example.demo.domain.EventPublisher;
import com.example.demo.domain.EventStore;
import com.example.demo.domain.MessageRepository;
import com.example.demo.domain.Timelines;
import com.example.demo.rightside.InMemoryEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
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

}
