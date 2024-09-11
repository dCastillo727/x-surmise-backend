package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.common.utils.mapping.ModifierMapper;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.client.ClientRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.jparepository.JpaRepositoryClient;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
    private final ModifierMapper<Client, ClientJpaEntity> clientModifier;

    @Override
    public void save(Client client) {
        if (client == null)
            throw new NullPointerException("Client cannot be null");

        if (jpaRepositoryClient.existsById(client.getId().id()))
            throw new EntityExistsException("Client with id " + client.getId().id() + " already exists");

        ClientJpaEntity entity = clientMapper.toEntity(client);
        jpaRepositoryClient.save(entity);
    }

    @Override
    public Optional<Client> findBySimpleId(ClientSimpleId clientId) {
        if (clientId == null) throw new NullPointerException("clientId cannot be null");

        return jpaRepositoryClient.findById(clientId.id()).map(clientMapper::toDomainModel);
    }

    @Override
    public Optional<Client> findByClientId(ClientId clientId) {
        if (clientId == null) throw new NullPointerException("clientId cannot be null");

        return jpaRepositoryClient.findByClientId(clientId.value()).map(clientMapper::toDomainModel);
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
        if (clientId == null) throw new NullPointerException("clientId cannot be null");

        ClientJpaEntity client = jpaRepositoryClient
                .findByClientId(clientId.value())
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + clientId.value() + " not found"));

        jpaRepositoryClient.delete(client);
    }

    @Override
    public Client update(Client client) {
        if (client == null) throw new NullPointerException("client cannot be null");

        final ClientJpaEntity foundClient = jpaRepositoryClient
                .findById(client.getId().id())
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + client.getId().id() + " not found"));

        final ClientJpaEntity modifiedClient = clientModifier
                .applyChangesFrom(client)
                .to(foundClient);

        jpaRepositoryClient.save(modifiedClient);
        return client;
    }
}
