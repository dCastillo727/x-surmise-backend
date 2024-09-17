package com.xsurmise.authorizationserver.layers.application.core.client.usecase;

import com.xsurmise.authorizationserver.layers.domain.model.client.Client;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientSimpleId;

import java.util.List;
import java.util.Optional;

public interface ClientGetUseCases {
    List<Client> getAllClients();

    Optional<Client> getByClientId(ClientId clientId);

    Optional<Client> getById(ClientSimpleId id);
}
