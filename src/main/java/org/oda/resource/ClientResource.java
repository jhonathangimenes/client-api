package org.oda.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.oda.dto.ClientDTO;
import org.oda.repository.ClientRepository;
import org.oda.resource.request.ClientRequest;
import org.oda.resource.response.ClientResponse;
import org.oda.type.LinkEnum;
import org.oda.usecase.CreateClientUseCase;
import org.oda.usecase.FindClientUseCase;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {
    private final ClientRepository clientRepository;

    ClientResource() {
        this.clientRepository = new ClientRepository();
    }

    @POST
    public Response create(@Context UriInfo uriInfo, @Valid ClientRequest clientRequest) {
        CreateClientUseCase createClientUseCase = new CreateClientUseCase(clientRepository);
        ClientDTO clientDTOResponse =  createClientUseCase.create(convertClientRequestToClientDTO(clientRequest));
        URI uri = uriInfo.getAbsolutePathBuilder().path(clientDTOResponse.id()).build();
        ClientResponse<ClientDTO> clientResponse = responseCreate(uri, clientDTOResponse);
        return Response.status(Response.Status.CREATED).entity(clientResponse).build();
    }

    @GET
    @Path("/{id}")
    public Response find(@Context UriInfo uriInfo, @PathParam("id") String id) {
        FindClientUseCase findClientUseCase = new FindClientUseCase(clientRepository);
        ClientDTO clientDTO = findClientUseCase.find(id);
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        ClientResponse<ClientDTO> clientResponse = responseFind(uri, clientDTO);
        return Response.ok(clientResponse).build();
    }

    private ClientDTO convertClientRequestToClientDTO(ClientRequest clientRequest) {
        return new ClientDTO(clientRequest.getName(), clientRequest.getEmail());
    }

    private ClientResponse<ClientDTO> responseCreate(URI uri, ClientDTO clientDTO) {
        List<LinkEnum> linksEnum = Arrays.asList(LinkEnum.SELF, LinkEnum.UPDATE, LinkEnum.DELETE);
        return buildResponse(uri, clientDTO, linksEnum);
    }

    private ClientResponse<ClientDTO> responseFind(URI uri, ClientDTO clientDTO) {
        List<LinkEnum> linksEnum = Arrays.asList(LinkEnum.UPDATE, LinkEnum.DELETE);
        return buildResponse(uri, clientDTO, linksEnum);
    }

    private ClientResponse<ClientDTO> buildResponse(URI uri, ClientDTO clientDTO, List<LinkEnum> linksEnum) {
        return new ClientResponse<>(clientDTO, uri, linksEnum);
    }
}
