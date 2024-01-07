package org.oda.resource.response;

import java.time.Instant;
import java.util.List;

public class ResponseError<T> {
    private final String title;
    private final List<T> errors;
    private final Instant requestDateTime;

    public ResponseError(String title, List<T> errors) {
        this.title = title;
        this.errors = errors;
        this.requestDateTime = Instant.now();
    }

    public String getTitle() {
        return title;
    }

    public List<T> getErrors() {
        return errors;
    }

    public Instant getRequestDateTime() {
        return requestDateTime;
    }
}
