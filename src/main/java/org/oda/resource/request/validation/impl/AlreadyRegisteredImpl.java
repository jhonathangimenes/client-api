package org.oda.resource.request.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.oda.repository.UserRepository;
import org.oda.resource.request.validation.AlreadyRegistered;

public class AlreadyRegisteredImpl implements ConstraintValidator<AlreadyRegistered, String> {
    @Override
    public void initialize(org.oda.resource.request.validation.AlreadyRegistered constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        UserRepository userRepository = new UserRepository();
        return userRepository.getByEmail(value) == null;
    }
}
