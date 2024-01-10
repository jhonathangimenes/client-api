package org.oda.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError<T> {
    private final String message;
    private final Set<T> errors;
    private final Instant requestDateTime;

    public ResponseError(String message, Set<T> errors) {
        this.message = message;
        this.errors = errors;
        this.requestDateTime = Instant.now();
    }

    public ResponseError(String message) {
        this.message = message;
        this.errors = null;
        this.requestDateTime = Instant.now();
    }

    public String getMessage() {
        return message;
    }

    public Set<T> getErrors() {
        return errors;
    }

    public Instant getRequestDateTime() {
        return requestDateTime;
    }
}
