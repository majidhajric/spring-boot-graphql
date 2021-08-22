package dev.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import dev.demo.graphql.model.Author;
import dev.demo.graphql.model.Book;
import dev.demo.graphql.repository.AuthorRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

/*
 * complex type resolver
 */
@RequiredArgsConstructor
@Component
public class BookResolver implements GraphQLResolver<Book> {

    private final AuthorRepository authorRepository;

    public Author getAuthor(Book book, Optional<UUID> bookId, DataFetchingEnvironment environment) {
        return authorRepository.findById(book.getAuthor().getId()).orElseThrow(EntityNotFoundException::new);
    }
}
