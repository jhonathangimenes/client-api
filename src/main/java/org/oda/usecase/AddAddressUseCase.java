package org.oda.usecase;

import org.oda.repository.AddressRepository;
import org.oda.repository.model.Address;

public class AddAddressUseCase {
    private final AddressRepository addressRepository;
    public AddAddressUseCase(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void execute(String userId, Address address) {
        addressRepository.addAddress(userId, address);
    }
}
