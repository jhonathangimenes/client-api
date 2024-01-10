package org.oda.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.oda.resource.response.ResponseError;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        Set<Map<String, String>> responseViolations = violations.stream()
                .map(violation -> {
                    Map<String, String> violationMap = new HashMap<>();
                    violationMap.put("field", captureNameField(violation.getPropertyPath()));
                    violationMap.put("message", violation.getMessageTemplate());
                    return violationMap;
                })
                .collect(Collectors.toSet());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ResponseError<>("Constraint violation", responseViolations))
                .build();
    }

    private String captureNameField(Path propertyPath) {
        String convertPropertyPathToString = propertyPath.toString();
        int lastIndexPoint = convertPropertyPathToString.lastIndexOf('.');
        return convertPropertyPathToString.substring(lastIndexPoint + 1);
    }
}




