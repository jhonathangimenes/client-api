package org.oda.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.ws.rs.NotFoundException;
import org.oda.repository.model.User;

import java.util.Optional;
import java.util.UUID;

public class UserRepository implements PanacheMongoRepositoryBase<User, UUID> {
    public UserRepository() {
    }

    public User getById(String id) {
        Optional<User> optional = this.findByIdOptional(UUID.fromString(id));
        return optional.orElseThrow(NotFoundException::new);
    }
    public User create(User user) {
        user.setId(UUID.randomUUID());
        user.persist();
        return getByEmail(user.getEmail());
    }

    public User getByEmail(String email) {
        return find("email", email).firstResult();
    }
}
