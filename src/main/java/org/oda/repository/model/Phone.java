package org.oda.repository.model;

import java.util.UUID;

public class Phone {
    private UUID id;
    private String number;
    private Boolean main;

    public Phone(String number, Boolean main) {
        this.number = number;
        this.main = main;
    }

    public Phone() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }
}
