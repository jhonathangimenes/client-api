package org.oda.repository.impl;

import jakarta.ws.rs.NotFoundException;
import org.oda.repository.AddressRepository;
import org.oda.repository.UserRepository;
import org.oda.repository.model.Address;
import org.oda.repository.model.User;

import java.util.*;

public class AddressRepositoryImpl implements AddressRepository {
    private final UserRepository userRepository;
    public AddressRepositoryImpl() {
        this.userRepository = new UserRepositoryImpl();
    }
    @Override
    public Address addAddress(String userId, Address address) {
        User user = userRepository.getById(userId);
        user.addAddress(address);
        user.update();
        return getByUserIdAndAddressDescription(userId, address.getDescription());
    }

    public Address getByUserIdAndAddressDescription(String userId, String description) {
        User user = userRepository.getById(userId);
        Optional<Address> optional = user.getAddresses().stream()
                .filter(address -> address.getDescription().equals(description)).findFirst();
        return optional.orElseThrow(NotFoundException::new);
    }

    public Boolean addressExists(String userId, String description) {
        User user = userRepository.getById(userId);
        return user.getAddresses().stream().anyMatch(address -> address.getDescription().equals(description));
    }
}
