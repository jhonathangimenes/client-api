package org.oda.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.oda.repository.AddressRepository;
import org.oda.repository.model.Address;
import org.oda.repository.model.Coordinate;
import org.oda.resource.request.AddAddressRequest;
import org.oda.resource.response.DefaultResponse;
import org.oda.type.LinkEnum;
import org.oda.usecase.AddAddressUseCase;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Path("clients/{id}/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {
    private final AddAddressUseCase addAddressUseCase;

    AddressResource() {
        this.addAddressUseCase = new AddAddressUseCase(new AddressRepository());
    }

    @POST
    public Response create(@Context UriInfo uriInfo, @PathParam("id") String id, @Valid AddAddressRequest addressRequest) {
        addAddressUseCase.execute(id, convertAddressResquestToAddress(addressRequest));
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        DefaultResponse<?> defaultResponse = responseAddAddress(uri);
        return Response.status(Response.Status.CREATED).entity(defaultResponse).build();
    }

    private Address convertAddressResquestToAddress(AddAddressRequest addressRequest) {
        Coordinate coordinate = new Coordinate(addressRequest.getCoordinateLongitude(), addressRequest.getCoordinateLatitude());
        return new Address(addressRequest.getDescription(), addressRequest.getAddress(), addressRequest.getNumber(), addressRequest.getComplement(), addressRequest.getCep(), addressRequest.getState(), addressRequest.getCity(), coordinate, false);
    }

    private DefaultResponse<?> responseAddAddress(URI uri) {
        List<LinkEnum> linksEnum = Arrays.asList(LinkEnum.SELF, LinkEnum.UPDATE, LinkEnum.DELETE);
        return new DefaultResponse<>(uri, linksEnum);
    }
}
