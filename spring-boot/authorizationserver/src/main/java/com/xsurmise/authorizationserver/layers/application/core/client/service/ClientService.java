package com.xsurmise.authorizationserver.layers.application.core.client.service;

import com.xsurmise.authorizationserver.layers.application.core.client.usecase.ClientCreateUseCase;
import com.xsurmise.authorizationserver.layers.application.core.client.usecase.ClientGetUseCases;
import com.xsurmise.authorizationserver.layers.application.port.driven.persistence.repository.client.ClientRepositoryDrivenPort;
import com.xsurmise.authorizationserver.layers.domain.command.client.ClientCreateCommand;
import com.xsurmise.authorizationserver.layers.domain.model.client.Client;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientSimpleId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements ClientGetUseCases, ClientCreateUseCase {
    private final ClientRepositoryDrivenPort clientRepositoryDrivenPort;

    @Override
    public List<Client> getAllClients() {
        return clientRepositoryDrivenPort.getClients();
    }

    @Override
    public Optional<Client> getByClientId(ClientId clientId) {
        if (clientId == null) throw new NullPointerException("clientId is null");

        return clientRepositoryDrivenPort.getClientBy(clientId);
    }

    @Override
    public Optional<Client> getById(ClientSimpleId id) {
        if (id == null) throw new NullPointerException("id is null");

        return clientRepositoryDrivenPort.getClientBy(id);
    }

    @Override
    public Client createClient(ClientCreateCommand command) {
        if (command == null) throw new NullPointerException("command is null");

        Client client = Client.createBy(command);
        ClientSimpleId createdId = clientRepositoryDrivenPort.saveClient(client);

        client.setId(createdId);
        return client;
    }
}
