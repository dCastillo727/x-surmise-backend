package com.xsurmise.authorizationdata.layers.application.core.client.service;

import com.xsurmise.authorizationdata.layers.application.core.client.usecase.ClientGetAllUseCase;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.client.ClientRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements ClientGetAllUseCase {
    private final ClientRepositoryDrivenPort clientRepository;

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        return List.copyOf(clients);
    }
}
