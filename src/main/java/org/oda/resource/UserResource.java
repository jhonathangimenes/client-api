package org.oda.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.oda.repository.UserRepository;
import org.oda.repository.model.User;
import org.oda.resource.request.UserRequest;
import org.oda.resource.response.DefaultResponse;
import org.oda.type.LinkEnum;
import org.oda.usecase.CreateUserUseCase;
import org.oda.usecase.FindUserUseCase;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserRepository userRepository;

    UserResource() {
        this.userRepository = new UserRepository();
    }

    @POST
    public Response create(@Context UriInfo uriInfo, @Valid UserRequest userRequest) {
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        User user =  createUserUseCase.execute(convertUserRequestToUser(userRequest));
        URI uri = uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();
        DefaultResponse<User> defaultResponse = responseCreate(uri, user);
        return Response.status(Response.Status.CREATED).entity(defaultResponse).build();
    }

    @GET
    @Path("/{id}")
    public Response find(@Context UriInfo uriInfo, @PathParam("id") String id) {
        FindUserUseCase findUserUseCase = new FindUserUseCase(userRepository);
        User user = findUserUseCase.execute(id);
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        DefaultResponse<User> defaultResponse = responseFind(uri, user);
        return Response.ok(defaultResponse).build();
    }

    private User convertUserRequestToUser(UserRequest userRequest) {
        return new User(userRequest.getName(), userRequest.getEmail());
    }

    private DefaultResponse<User> responseCreate(URI uri, User user) {
        List<LinkEnum> linksEnum = Arrays.asList(LinkEnum.SELF, LinkEnum.UPDATE, LinkEnum.DELETE);
        return buildResponse(uri, user, linksEnum);
    }

    private DefaultResponse<User> responseFind(URI uri, User user) {
        List<LinkEnum> linksEnum = Arrays.asList(LinkEnum.UPDATE, LinkEnum.DELETE);
        return buildResponse(uri, user, linksEnum);
    }

    private DefaultResponse<User> buildResponse(URI uri, User user, List<LinkEnum> linksEnum) {
        return new DefaultResponse<>(user, uri, linksEnum);
    }
}
