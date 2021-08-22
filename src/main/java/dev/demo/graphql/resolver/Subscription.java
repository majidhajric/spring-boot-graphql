package dev.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import dev.demo.graphql.event.BookCreatedEvent;
import dev.demo.graphql.model.Book;
import org.reactivestreams.Publisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

    private final Flux<Book> publisher;

    FluxSink<Book> bookEmitter;

    public Subscription() {
        Flux<Book> bookFlux = Flux.create( emitter -> {
                            bookEmitter = emitter;
                        },
                        FluxSink.OverflowStrategy.BUFFER);
        ConnectableFlux<Book> connectableFlux = bookFlux.share().publish();
        connectableFlux.connect();

        publisher = Flux.from(connectableFlux);
    }

    @EventListener(BookCreatedEvent.class)
    public void onBookCreated(BookCreatedEvent event) {
        Book book = event.getBook();
        bookEmitter.next(book);
    }

    public Publisher<Book> bookCreated() {
        return publisher;
    }
}
