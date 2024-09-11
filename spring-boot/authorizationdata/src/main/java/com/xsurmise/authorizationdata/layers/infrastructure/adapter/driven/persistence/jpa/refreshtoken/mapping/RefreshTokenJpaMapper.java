package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshToken;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshTokenDate;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshTokenId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.entity.RefreshTokenJpaEntity;

public class RefreshTokenJpaMapper implements MapperEntity<RefreshToken, RefreshTokenJpaEntity> {
    @Override
    public RefreshTokenJpaEntity toEntity(RefreshToken domainModel) {
        return RefreshTokenJpaEntity.builder()
                .tokenId(domainModel.getTokenId().asUUID())
                .userId(domainModel.getUserId().asUUID())
                .clientId(domainModel.getClientId().value())
                .issuedAt(domainModel.getIssuedAt().toOffsetDateTime())
                .expiresAt(domainModel.getExpiresAt().toOffsetDateTime())
                .build();
    }

    @Override
    public RefreshToken toDomainModel(RefreshTokenJpaEntity entity) {
        return new RefreshToken(
                RefreshTokenId.from(entity.getTokenId()),
                AppUserId.from(entity.getUserId()),
                ClientId.from(entity.getClientId()),
                RefreshTokenDate.from(entity.getIssuedAt()),
                RefreshTokenDate.from(entity.getExpiresAt())
        );
    }
}
