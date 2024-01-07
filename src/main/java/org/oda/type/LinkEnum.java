package org.oda.type;

import java.net.URI;

public enum LinkEnum {
    SELF("self", "GET"),
    UPDATE("update", "PUT"),
    DELETE("delete", "DELETE");

    private final String description;
    private final String method;
    private URI uri;

    LinkEnum(String description, String method) {
        this.description = description;
        this.method = method;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMethod() {
        return this.method;
    }

    public URI getUri() {
        return this.uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
