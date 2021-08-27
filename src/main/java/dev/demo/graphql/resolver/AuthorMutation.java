package dev.demo.graphql.resolver;

import dev.demo.graphql.model.Author;
import dev.demo.graphql.repository.AuthorRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorMutation implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepository.save(author);

        return author;
    }
}
