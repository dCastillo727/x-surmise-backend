package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.client.ClientRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.jparepository.JpaRepositoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class ClientRepositoryDrivenAdapter implements ClientRepositoryDrivenPort {
    private final JpaRepositoryClient jpaRepositoryClient;

    private final MapperEntity<Client, ClientJpaEntity> clientMapper;

    @Override
    public void save(Client client) {

    }

    @Override
    public Optional<Client> findBySimpleId(ClientSimpleId clientId) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> findByClientId(ClientId clientId) {
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        List<ClientJpaEntity> clientEntities = jpaRepositoryClient.findAll();

        if (clientEntities.isEmpty()) return List.of();

        List<Client> clients = new ArrayList<>();
        for (ClientJpaEntity clientEntity : clientEntities) {
            clients.add(clientMapper.toDomainModel(clientEntity));
        }

        return clients;
    }

    @Override
    public void deleteByClientId(ClientId clientId) {

    }

    @Override
    public Client update(Client client) {
        return null;
    }
}
