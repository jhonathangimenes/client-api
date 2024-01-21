package org.oda.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.oda.repository.impl.AddressRepositoryImpl;
import org.oda.repository.model.Address;
import org.oda.repository.model.Coordinate;
import org.oda.resource.request.AddAddressRequest;
import org.oda.usecase.AddAddressUseCase;
import org.oda.usecase.impl.AddAddressUseCaseImpl;

@Path("clients/{userId}/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {
    private final AddAddressUseCase addAddressUseCase;

    AddressResource() {
        this.addAddressUseCase = new AddAddressUseCaseImpl(new AddressRepositoryImpl());
    }

    @POST
    public Response create(@PathParam("userId") String userId, @Valid AddAddressRequest addressRequest) {
        Address address = addAddressUseCase.execute(userId, convertAddressResquestToAddress(addressRequest));
        return Response.status(Response.Status.CREATED).entity(address).build();
    }

    public Response update(){
        return null;
    }

    private Address convertAddressResquestToAddress(AddAddressRequest addressRequest) {
        Coordinate coordinate = new Coordinate(addressRequest.getCoordinateLongitude(), addressRequest.getCoordinateLatitude());
        return new Address(addressRequest.getDescription(), addressRequest.getAddress(), addressRequest.getNumber(), addressRequest.getComplement(), addressRequest.getCep(), addressRequest.getState(), addressRequest.getCity(), coordinate, false);
    }
}
