package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.mapping;

import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshToken;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.entity.RefreshTokenJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.utils.jpa.PageMapper;

import java.util.function.Function;

public class RefreshTokenPageMapper extends PageMapper<RefreshToken, RefreshTokenJpaEntity> {
    public RefreshTokenPageMapper(RefreshTokenJpaMapper mapper) {
        super(mapper::toDomainModel);
    }
}
