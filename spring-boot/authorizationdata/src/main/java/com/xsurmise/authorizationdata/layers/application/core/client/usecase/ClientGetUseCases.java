package com.xsurmise.authorizationdata.layers.application.core.client.usecase;

import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;

import java.util.List;
import java.util.Optional;

public interface ClientGetUseCases {
    List<Client> getAllClients();

    Optional<Client> getClientBy(ClientId clientId);

    Optional<Client> getClientBy(ClientSimpleId id);
}
