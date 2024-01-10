package org.oda.repository.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MongoEntity(collection="user")
public class User extends PanacheMongoEntityBase {
    private UUID id;
    private String name;
    private String email;
    private List<Phone> phones;
    private List<Address> addresses;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, List<Address> addresses) {
        this.name = name;
        this.email = email;
    }

    public User() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        if (phones == null) {
            return new ArrayList<>();
        }
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public List<Address> getAddresses() {
        if (addresses == null) {
            return new ArrayList<>();
        }
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        address.setId(UUID.randomUUID());
        this.addresses.add(address);
    }
}

