package org.oda.repository;

import org.oda.repository.model.User;

public interface UserRepository {
    User create(User user);
    User getById(String id);
    User getByEmail(String email);
}
