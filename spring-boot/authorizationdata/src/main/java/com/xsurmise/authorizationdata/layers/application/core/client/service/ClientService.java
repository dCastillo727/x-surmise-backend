package com.xsurmise.authorizationdata.layers.application.core.client.service;

import com.xsurmise.authorizationdata.layers.application.core.client.usecase.ClientCreateUseCase;
import com.xsurmise.authorizationdata.layers.application.core.client.usecase.ClientGetUseCases;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.client.ClientRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.command.client.CreateClientCommand;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements ClientGetUseCases, ClientCreateUseCase {
    private final ClientRepositoryDrivenPort clientRepository;

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        return List.copyOf(clients);
    }

    @Override
    public Optional<Client> getClientBy(ClientId clientId) {
        if (clientId == null) throw new IllegalArgumentException("clientId cannot be null");

        //TODO add logs

        return clientRepository.findByClientId(clientId);
    }

    @Override
    public Optional<Client> getClientBy(ClientSimpleId id) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");

        return clientRepository.findBySimpleId(id);
    }

    @Override
    public Client createClient(CreateClientCommand command) {
        Optional<Client> clientOptional = clientRepository.findByClientId(command.clientId());

        if (clientOptional.isPresent())
            throw new EntityExistsException("client already exists");

        final Client client = Client.createBy(command);

        clientRepository.save(client);
        return client;
    }
}
