package org.oda.repository;

import org.oda.repository.model.Address;
import org.oda.repository.model.User;

public class AddressRepository {
    private final UserRepository userRepository;
    public AddressRepository() {
        this.userRepository = new UserRepository();
    }

    public void addAddress(String userId, Address address) {
        User user = userRepository.getById(userId);
        user.addAddress(address);
        user.update();
    }
}
