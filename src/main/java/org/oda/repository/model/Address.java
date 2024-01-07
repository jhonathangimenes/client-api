package org.oda.repository.model;

class Address {
    String description;
    String address;
    String number;
    String complement;
    String cep;
    String state;
    String city;
    Boolean main;

    public Address(String description, String address, String number, String complement, String cep, String state, String city, Boolean main) {
        this.description = description;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public Boolean getMain() {
        return main;
    }
}
