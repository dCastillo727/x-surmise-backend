package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.mapping;

import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessToken;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.entity.AccessTokenJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.utils.jpa.PageMapper;

public class AccessTokenPageMapper extends PageMapper<AccessToken, AccessTokenJpaEntity> {
    public AccessTokenPageMapper(AccessTokenJpaMapper mapper) {
        super(mapper::toDomainModel);
    }
}
