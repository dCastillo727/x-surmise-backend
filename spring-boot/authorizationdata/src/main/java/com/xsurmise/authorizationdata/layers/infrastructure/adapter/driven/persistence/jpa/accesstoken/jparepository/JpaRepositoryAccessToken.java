package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.entity.AccessTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface JpaRepositoryAccessToken extends JpaRepository<AccessTokenJpaEntity, UUID> {
}
