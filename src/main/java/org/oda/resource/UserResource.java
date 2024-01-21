package org.oda.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.oda.repository.UserRepository;
import org.oda.repository.impl.UserRepositoryImpl;
import org.oda.repository.model.User;
import org.oda.resource.request.UserRequest;
import org.oda.usecase.impl.CreateUserUseCaseImpl;
import org.oda.usecase.impl.FindUserUseCaseImpl;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserRepository userRepository;

    UserResource() {
        this.userRepository = new UserRepositoryImpl();
    }

    @POST
    public Response create(@Valid UserRequest userRequest) {
        CreateUserUseCaseImpl createUserUseCaseImpl = new CreateUserUseCaseImpl(userRepository);
        User user =  createUserUseCaseImpl.execute(convertUserRequestToUser(userRequest));
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") String id) {
        FindUserUseCaseImpl findUserUseCaseImpl = new FindUserUseCaseImpl(userRepository);
        User user = findUserUseCaseImpl.execute(id);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    public void update() {
    }

    @DELETE
    @Path("/{id}")
    public void delete() {
    }

    private User convertUserRequestToUser(UserRequest userRequest) {
        return new User(userRequest.getName(), userRequest.getEmail());
    }
}
