package org.oda.usecase;

import org.oda.repository.model.User;

public interface FindUserUseCase {
    User execute(String id);
}
