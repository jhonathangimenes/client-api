package org.oda.usecase;

import jakarta.ws.rs.NotFoundException;
import org.oda.dto.ClientDTO;
import org.oda.repository.ClientRepository;

public class CreateClientUseCase {
    ClientRepository clientRepository;
    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO create(ClientDTO clientDTO) {
        return clientRepository.create(clientDTO);
    }
}
