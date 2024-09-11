package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListToken;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListTokenDate;
import com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken.BlackListTokenId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.entity.BlackListTokenJpaEntity;

public class BlackListTokenJpaMapper implements MapperEntity<BlackListToken, BlackListTokenJpaEntity> {
    @Override
    public BlackListTokenJpaEntity toEntity(BlackListToken domainModel) {
        return BlackListTokenJpaEntity.builder()
                .tokenId(domainModel.getTokenId().asUUID())
                .revokedAt(domainModel.getRevokedAt().toOffsetDateTime())
                .build();
    }

    @Override
    public BlackListToken toDomainModel(BlackListTokenJpaEntity entity) {
        return new BlackListToken(
                BlackListTokenId.from(entity.getTokenId()),
                BlackListTokenDate.from(entity.getRevokedAt())
        );
    }
}
