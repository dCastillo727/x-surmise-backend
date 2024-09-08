package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.AppUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(isolation = Isolation.READ_COMMITTED)
public interface JpaRepositoryAppUser extends JpaRepository<AppUserJpaEntity, UUID> {
}
