package com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.client;

import com.xsurmise.authorizationdata.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;

import java.util.List;
import java.util.Optional;

@DrivenPort
public interface ClientRepositoryDrivenPort {
    void save(Client client);

    Optional<Client> findBySimpleId(ClientSimpleId clientId);

    Optional<Client> findByClientId(ClientId clientId);

    List<Client> findAll();

    void deleteByClientId(ClientId clientId);

    Client update(Client client);
}
