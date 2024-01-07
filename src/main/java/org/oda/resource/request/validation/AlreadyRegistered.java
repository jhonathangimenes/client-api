package org.oda.resource.request.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.oda.resource.request.validation.impl.AlreadyRegisteredImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlreadyRegisteredImpl.class)
public @interface AlreadyRegistered {
    String message() default "Already registered client";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
