package com.xsurmise.authorizationserver.layers.application.port.driven.persistence.repository.client;

import com.xsurmise.authorizationserver.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationserver.layers.domain.model.client.Client;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientSimpleId;

import java.util.List;
import java.util.Optional;

@DrivenPort
public interface ClientRepositoryDrivenPort {
    List<Client> getClients();

    Optional<Client> getClientBy(ClientId clientId);

    Optional<Client> getClientBy(ClientSimpleId id);

    ClientSimpleId saveClient(Client client);
}
