package dev.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import dev.demo.graphql.exceptions.BookNotFoundException;
import dev.demo.graphql.model.Author;
import dev.demo.graphql.model.Book;
import dev.demo.graphql.repository.AuthorRepository;
import dev.demo.graphql.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class Query implements GraphQLQueryResolver {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public Book bookById(UUID bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

}
