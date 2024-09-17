package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.GlobalUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(isolation = Isolation.READ_COMMITTED)
public interface JpaRepositoryGlobalUser extends JpaRepository<GlobalUserJpaEntity, UUID> {
    Optional<GlobalUserJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<GlobalUserJpaEntity> findByPhone(String phone);

    boolean existsByPhone(String phone);
}
