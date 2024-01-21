package org.oda.usecase;

import org.oda.repository.model.Address;

public interface AddAddressUseCase {
    Address execute(String userId, Address address);
}
