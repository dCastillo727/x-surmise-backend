package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessToken;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessTokenDate;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessTokenId;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.client.Scope;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.entity.AccessTokenJpaEntity;

import java.util.Arrays;

public class AccessTokenJpaMapper implements MapperEntity<AccessToken, AccessTokenJpaEntity> {
    @Override
    public AccessTokenJpaEntity toEntity(AccessToken domainModel) {
        return AccessTokenJpaEntity.builder()
                .tokenId(domainModel.getTokenId().asUUID())
                .userId(domainModel.getUserId().asUUID())
                .clientId(domainModel.getClientId().value())
                .scopes(String.join(" ", domainModel.getScopes().stream().map(Scope::getValue).toList()))
                .issuedAt(domainModel.getIssuedAt().toOffsetDateTime())
                .expiresAt(domainModel.getExpiresAt().toOffsetDateTime())
                .build();
    }

    @Override
    public AccessToken toDomainModel(AccessTokenJpaEntity entity) {
        return new AccessToken(
                AccessTokenId.from(entity.getTokenId()),
                AppUserId.from(entity.getUser().getId()),
                ClientId.from(entity.getClient().getClientId()),
                Arrays.stream(entity.getScopes().split(" ")).map(Scope::fromValue).toList(),
                AccessTokenDate.from(entity.getIssuedAt()),
                AccessTokenDate.from(entity.getExpiresAt())
        );
    }
}
