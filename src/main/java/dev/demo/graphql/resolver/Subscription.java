package dev.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import dev.demo.graphql.model.Book;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Component
public class Subscription implements GraphQLSubscriptionResolver {

    private static final Integer LARGE_BOOK_PAGE_COUNT = 100;

    private final Flux<Book> bookEvents;

    private boolean isLargeBook(Book book) {
        return book.getPageCount() > LARGE_BOOK_PAGE_COUNT;
    }

    public Publisher<Book> bookCreated(DataFetchingEnvironment environment) {
        return bookEvents
                .filter(this::isLargeBook);
    }
}
