package dev.demo.graphql.repository;

import dev.demo.graphql.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends CrudRepository<Author, UUID> {
}
