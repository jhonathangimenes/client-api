package org.oda.usecase;

import org.oda.repository.model.User;

public interface CreateUserUseCase {
    User execute(User user);
}
