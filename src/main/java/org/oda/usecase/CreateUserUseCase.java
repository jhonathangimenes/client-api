package org.oda.usecase;

import org.oda.repository.UserRepository;
import org.oda.repository.model.User;

public class CreateUserUseCase {
    UserRepository userRepository;
    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        return userRepository.create(user);
    }
}
