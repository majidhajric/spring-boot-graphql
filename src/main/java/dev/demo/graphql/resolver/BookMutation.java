package dev.demo.graphql.resolver;

import dev.demo.graphql.model.Author;
import dev.demo.graphql.model.Book;
import dev.demo.graphql.repository.AuthorRepository;
import dev.demo.graphql.repository.BookRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks.Many;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookMutation implements GraphQLMutationResolver {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final Many<Book> bookSink;

    public Book newBook(String title, Integer pageCount, UUID authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setPageCount(pageCount != null ? pageCount : 0);

        book = bookRepository.save(book);

        bookSink.tryEmitNext(book);

        return book;
    }

    public boolean deleteBook(UUID id) {
        bookRepository.deleteById(id);
        return true;
    }
}
