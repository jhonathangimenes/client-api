package org.oda.usecase.impl;

import org.oda.repository.UserRepository;
import org.oda.repository.model.User;
import org.oda.usecase.CreateUserUseCase;

public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    public CreateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(User user) {
        return userRepository.create(user);
    }
}
