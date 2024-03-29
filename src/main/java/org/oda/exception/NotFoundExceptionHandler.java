package org.oda.exception;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.oda.resource.response.ResponseError;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ResponseError<>("User not found"))
                .build();
    }
}
