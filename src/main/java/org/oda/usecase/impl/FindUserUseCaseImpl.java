package org.oda.usecase.impl;

import org.oda.repository.UserRepository;
import org.oda.repository.model.User;
import org.oda.usecase.FindUserUseCase;

public class FindUserUseCaseImpl implements FindUserUseCase {
    private final UserRepository userRepository;

    public FindUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User execute(String id) {
       return userRepository.getById(id);
    }
}
