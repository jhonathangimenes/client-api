package org.oda.repository.model;

class Phone {
    private final String number;
    private final Boolean main;

    public Phone(String number, Boolean main) {
        this.number = number;
        this.main = main;
    }

    public String getNumber() {
        return number;
    }

    public Boolean getMain() {
        return main;
    }
}
