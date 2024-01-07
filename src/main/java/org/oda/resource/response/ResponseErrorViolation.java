package org.oda.resource.response;

public class ResponseErrorViolation {
    private final String field;
    private final String message;

    public ResponseErrorViolation(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
