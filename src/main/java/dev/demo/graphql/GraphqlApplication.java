package dev.demo.graphql;

import dev.demo.graphql.model.Author;
import dev.demo.graphql.model.Book;
import dev.demo.graphql.repository.AuthorRepository;
import dev.demo.graphql.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
		return args -> {
			Author author = new Author();
			author.setFirstName("Me");
			author.setLastName("Developer");
			author = authorRepository.save(author);

			Book book = new Book();
			book.setTitle("Java: A Beginner's Guide, Sixth Edition");
			book.setPageCount(728);
			book.setAuthor(author);
			bookRepository.save(book);
		};
	}
}
