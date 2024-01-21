package org.oda.repository;

import org.oda.repository.model.Address;

public interface AddressRepository {
    Address addAddress(String userId, Address address);
    Address getByUserIdAndAddressDescription(String userId, String description);
    Boolean addressExists(String userId, String description);
}
