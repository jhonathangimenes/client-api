package org.oda.usecase.impl;

import org.oda.repository.AddressRepository;
import org.oda.repository.model.Address;
import org.oda.usecase.AddAddressUseCase;

public class AddAddressUseCaseImpl implements AddAddressUseCase {

    private final AddressRepository addressRepository;
    public AddAddressUseCaseImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address execute(String userId, Address address) {
        return addressRepository.addAddress(userId, address);
    }
}
