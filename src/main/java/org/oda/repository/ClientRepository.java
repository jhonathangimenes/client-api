package org.oda.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.ws.rs.NotFoundException;
import org.oda.dto.ClientDTO;
import org.oda.repository.model.Client;

import java.util.Optional;
import java.util.UUID;

public class ClientRepository implements PanacheMongoRepositoryBase<Client, UUID> {
    public ClientRepository() {
    }

    public ClientDTO getById(String id) {
        Optional<Client> optional = this.findByIdOptional(UUID.fromString(id));
        Client client =  optional.orElseThrow(NotFoundException::new);
        return convertClientForClientDTO(client);
    }
    public ClientDTO create(ClientDTO clientDTO) {
        Client client = convertClientDTOForClient(clientDTO);
        client.setId(UUID.randomUUID());
        client.persist();
        return convertClientForClientDTO(getByEmail(clientDTO.email()));
    }

    public Client getByEmail(String email) {
        return find("email", email).firstResult();
    }

    private Client convertClientDTOForClient(ClientDTO clientDTO) {
        return new Client(clientDTO.name(), clientDTO.email());
    }

    private ClientDTO convertClientForClientDTO(Client client) {
        return new ClientDTO(client.getId().toString(), client.getName(), client.getEmail());
    }
}
