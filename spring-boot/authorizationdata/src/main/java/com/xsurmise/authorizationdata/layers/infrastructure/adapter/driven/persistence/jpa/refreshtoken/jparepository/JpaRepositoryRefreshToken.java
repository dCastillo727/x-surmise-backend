package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.entity.RefreshTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface JpaRepositoryRefreshToken extends JpaRepository<RefreshTokenJpaEntity, UUID> {
}
