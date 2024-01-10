package org.oda.repository.model;

import java.util.UUID;

public class Address {
    private UUID id;
    private String description;
    private String address;
    private String number;
    private String complement;
    private String cep;
    private String state;
    private String city;
    private Coordinate coordinate;
    private Boolean main;

    public Address(String description, String address, String number, String complement, String cep, String state, String city, Coordinate coordinate, Boolean main) {
        this.description = description;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.coordinate = coordinate;
        this.main = main;
    }

    public Address() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }
}
