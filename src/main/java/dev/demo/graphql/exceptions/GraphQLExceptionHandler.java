package dev.demo.graphql.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphQLExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public GraphQLError handleBookNotFoundException(BookNotFoundException e) {
        return e;
    }

    @ExceptionHandler(GraphqlErrorException.class)
    public GraphQLError handleGraphQLException(GraphqlErrorException e) {
        return e;
    }

    @ExceptionHandler(Throwable.class)
    public GraphQLError handleInternalExceptions(Throwable e) {
        return new ThrowableGraphQLError(e, "Internal server error");
    }
}
