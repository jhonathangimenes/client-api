package org.oda.resource.request.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.oda.resource.request.validation.impl.AlreadyRegisteredEmailUserImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlreadyRegisteredEmailUserImpl.class)
public @interface AlreadyRegisteredEmailUser {
    String message() default "Already registered user";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
