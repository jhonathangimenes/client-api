package org.oda.resource.request.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import org.oda.repository.AddressRepository;
import org.oda.repository.impl.AddressRepositoryImpl;
import org.oda.resource.request.validation.AlreadyRegisteredDescriptionAddress;

public class AlreadyRegisteredDescriptionAddressImpl implements ConstraintValidator<AlreadyRegisteredDescriptionAddress, String> {

    @Context
    UriInfo uriInfo;

    @Override
    public void initialize(AlreadyRegisteredDescriptionAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        MultivaluedMap<String, String> parameters = uriInfo.getPathParameters();
        String userId = parameters.get("userId").get(0);
        AddressRepository addressRepository = new AddressRepositoryImpl();
        return !addressRepository.addressExists(userId, value);
    }
}
