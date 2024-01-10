package org.oda.type;

public enum LinkEnum {
    SELF("self", "GET"),
    UPDATE("update", "PUT"),
    DELETE("delete", "DELETE");

    private final String description;
    private final String method;

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
}
