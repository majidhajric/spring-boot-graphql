package dev.demo.graphql.config;

import dev.demo.graphql.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
import reactor.util.concurrent.Queues;

@Configuration
public class SubscriptionConfig {

    @Bean
    public Many<Book> bookSink() {
        return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
    }

    @Bean
    public Flux<Book> bookFlux(Many<Book> bookSink) {
        return bookSink.asFlux();
    }
}
