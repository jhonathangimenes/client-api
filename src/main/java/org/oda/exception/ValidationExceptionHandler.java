package org.oda.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.oda.resource.response.ResponseError;
import org.oda.resource.response.ResponseErrorViolation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<ResponseErrorViolation> responseViolations = violations.stream()
                .map(violation -> new ResponseErrorViolation(captureNameField(violation.getPropertyPath()), violation.getMessageTemplate()))
                .collect(Collectors.toList());
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




