package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.domain.model.client.*;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;

import java.util.Arrays;

public class ClientJpaMapper implements MapperEntity<Client, ClientJpaEntity> {
    @Override
    public ClientJpaEntity toEntity(Client domainModel) {
        return ClientJpaEntity.builder()
                .id(domainModel.getId().id())
                .clientId(domainModel.getClientId().value())
                .clientSecret(domainModel.getClientSecret().value())
                .accessTokenValidity(domainModel.getAccessTokenValidity().duration())
                .refreshTokenValidity(domainModel.getRefreshTokenValidity().duration())
                .createdAt(domainModel.getCreatedAt().toOffsetDateTime())
                .updatedAt(domainModel.getUpdatedAt().toOffsetDateTime())
                .redirectUris(domainModel.getRedirectUris().stream().map(RedirectUri::uri).toArray(String[]::new))
                .scopes(domainModel.getScopes().stream().map(Scope::getValue).toArray(String[]::new))
                .grantTypes(domainModel.getGrantTypes().stream().map(GrantType::getValue).toArray(String[]::new))
                .build();
    }

    @Override
    public Client toDomainModel(ClientJpaEntity entity) {
        return new Client(
                ClientSimpleId.from(entity.getId()),
                ClientId.from(entity.getClientId()),
                ClientSecret.from(entity.getClientSecret()),
                Arrays.stream(entity.getGrantTypes()).map(GrantType::fromValue).toList(),
                Arrays.stream(entity.getRedirectUris()).map(RedirectUri::new).toList(),
                Arrays.stream(entity.getScopes()).map(Scope::fromValue).toList(),
                TokenValidity.of(entity.getAccessTokenValidity()),
                TokenValidity.of(entity.getRefreshTokenValidity()),
                ClientDate.from(entity.getCreatedAt()),
                ClientDate.from(entity.getUpdatedAt())
        );
    }
}
