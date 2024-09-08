package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.entity.BlackListTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface JpaRepositoryBlackListToken extends JpaRepository<BlackListTokenJpaEntity, UUID> {
}
