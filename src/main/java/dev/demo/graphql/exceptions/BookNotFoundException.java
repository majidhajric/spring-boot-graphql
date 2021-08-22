package dev.demo.graphql.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public BookNotFoundException(UUID invalidBookId) {
        super("Book not found");
        extensions.put("invalidBookId", invalidBookId);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}

