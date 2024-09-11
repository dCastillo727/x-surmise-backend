package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.jparepository;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.entity.AccessTokenJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface JpaRepositoryAccessToken extends JpaRepository<AccessTokenJpaEntity, UUID> {
    Page<AccessTokenJpaEntity> findAllByUserId(Pageable pageable, UUID userId);

    @Query("SELECT at FROM AccessTokenJpaEntity at where at.user.globalUserId = :gbUserId")
    Page<AccessTokenJpaEntity> findAllByGlobalUserId(Pageable pageable, @Param("gbUserId") UUID globalUserId);

    void deleteByUserId(UUID userId);

    @Modifying
    @Query("DELETE AccessTokenJpaEntity at where at.user.globalUserId = :gbUserId")
    void deleteByGlobalUserId(@Param("gbUserId") UUID globalUserId);
}
