package org.oda.dto;

public record ClientDTO(String id, String name, String email) {
    public ClientDTO(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public ClientDTO(String name, String email) {
        this(null, name, email);
    }
}

