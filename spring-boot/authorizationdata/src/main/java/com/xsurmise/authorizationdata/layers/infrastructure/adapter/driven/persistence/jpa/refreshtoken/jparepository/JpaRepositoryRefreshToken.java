package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.entity.RefreshTokenJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface JpaRepositoryRefreshToken extends JpaRepository<RefreshTokenJpaEntity, UUID> {
    Page<RefreshTokenJpaEntity> findAllByUserId(Pageable pageable, UUID userId);

    @Query("SELECT rt FROM RefreshTokenJpaEntity rt where rt.appUser.globalUserId = :gbUserId")
    Page<RefreshTokenJpaEntity> findAllByGlobalUserId(Pageable pageable, @Param("gbUserId") UUID globalUserId);

    void deleteByUserId(UUID userId);

    @Modifying
    @Query("DELETE RefreshTokenJpaEntity rt WHERE rt.appUser.globalUserId = :gbUserId")
    void deleteByGlobalUserId(UUID globalUserId);
}
