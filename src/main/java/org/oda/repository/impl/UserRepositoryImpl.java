package org.oda.repository.impl;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.ws.rs.NotFoundException;
import org.oda.repository.UserRepository;
import org.oda.repository.model.User;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements PanacheMongoRepositoryBase<User, UUID>, UserRepository {
    public UserRepositoryImpl() {
    }

    @Override
    public User create(User user) {
        user.setId(UUID.randomUUID());
        user.persist();
        return getByEmail(user.getEmail());
    }
    @Override
    public User getById(String id) {
        Optional<User> optional = this.findByIdOptional(UUID.fromString(id));
        return optional.orElseThrow(NotFoundException::new);
    }
    @Override
    public User getByEmail(String email) {
        return find("email", email).firstResult();
    }
}
