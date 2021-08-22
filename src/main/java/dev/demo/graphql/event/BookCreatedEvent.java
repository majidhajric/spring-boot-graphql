package dev.demo.graphql.event;

import dev.demo.graphql.model.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BookCreatedEvent {

    private final Book book;
}
