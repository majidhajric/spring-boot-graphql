package dev.demo.graphql.repository;

import dev.demo.graphql.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {
}
