package org.oda.usecase;

import org.oda.repository.UserRepository;
import org.oda.repository.model.User;

public class FindUserUseCase {
    private final UserRepository userRepository;

    public FindUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String id) {
       return userRepository.getById(id);
    }
}
