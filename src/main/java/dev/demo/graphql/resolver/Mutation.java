package dev.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import dev.demo.graphql.event.BookCreatedEvent;
import dev.demo.graphql.model.Author;
import dev.demo.graphql.model.Book;
import dev.demo.graphql.repository.AuthorRepository;
import dev.demo.graphql.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class Mutation implements GraphQLMutationResolver, ApplicationEventPublisherAware {

    private final ResourceLoader resourceLoader;

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private ApplicationEventPublisher eventPublisher;

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepository.save(author);

        return author;
    }

    public Book newBook(String title, Integer pageCount, UUID authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setPageCount(pageCount != null ? pageCount : 0);

        book = bookRepository.save(book);

        eventPublisher.publishEvent(new BookCreatedEvent(book));

        return book;
    }

    public boolean deleteBook(UUID id) {
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
