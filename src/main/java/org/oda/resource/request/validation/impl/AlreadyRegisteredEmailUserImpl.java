package org.oda.resource.request.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.oda.repository.UserRepository;
import org.oda.repository.impl.UserRepositoryImpl;
import org.oda.resource.request.validation.AlreadyRegisteredEmailUser;

public class AlreadyRegisteredEmailUserImpl implements ConstraintValidator<AlreadyRegisteredEmailUser, String> {
    @Override
    public void initialize(AlreadyRegisteredEmailUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        UserRepository userRepository = new UserRepositoryImpl();
        return userRepository.getByEmail(value) == null;
    }
}
