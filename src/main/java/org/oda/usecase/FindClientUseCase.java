package org.oda.usecase;

import org.oda.dto.ClientDTO;
import org.oda.repository.ClientRepository;

public class FindClientUseCase {
    private final ClientRepository clientRepository;

    public FindClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO find(String id) {
       return clientRepository.getById(id);
    }
}
