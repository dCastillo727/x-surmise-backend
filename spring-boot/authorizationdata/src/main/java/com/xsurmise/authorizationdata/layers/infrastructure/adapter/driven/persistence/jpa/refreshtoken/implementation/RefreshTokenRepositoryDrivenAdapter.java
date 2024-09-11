package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.application.dto.utils.response.pagination.AppPage;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.refreshtoken.RefreshTokenRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshToken;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshTokenId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.entity.RefreshTokenJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.jparepository.JpaRepositoryRefreshToken;
import com.xsurmise.authorizationdata.layers.infrastructure.utils.jpa.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class RefreshTokenRepositoryDrivenAdapter implements RefreshTokenRepositoryDrivenPort {
    private final JpaRepositoryRefreshToken jpaRepositoryRefreshToken;

    private final MapperEntity<RefreshToken, RefreshTokenJpaEntity> refreshTokenEntityMapper;
    private final PageMapper<RefreshToken, RefreshTokenJpaEntity> refreshTokenPageMapper;

    @Override
    public void save(RefreshToken refreshToken) {
        if (refreshToken == null) throw new NullPointerException("Refresh token is null");

        RefreshTokenJpaEntity entity = refreshTokenEntityMapper.toEntity(refreshToken);

        jpaRepositoryRefreshToken.save(entity);
    }

    @Override
    public Optional<RefreshToken> findById(RefreshTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("tokenId is null");

        return jpaRepositoryRefreshToken.findById(tokenId.asUUID()).map(refreshTokenEntityMapper::toDomainModel);
    }

    @Override
    public boolean existsById(RefreshTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("tokenId is null");

        return jpaRepositoryRefreshToken.existsById(tokenId.asUUID());
    }

    @Override
    public AppPage<RefreshToken> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<RefreshTokenJpaEntity> entities = jpaRepositoryRefreshToken.findAll(pageable);

        return refreshTokenPageMapper.toAppPage(entities);
    }

    @Override
    public AppPage<RefreshToken> findAllByAppUserId(AppUserId appUserId, int page, int pageSize) {
        if (appUserId == null) throw new NullPointerException("appUserId is null");

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<RefreshTokenJpaEntity> entities = jpaRepositoryRefreshToken.findAllByUserId(pageable, appUserId.asUUID());

        return refreshTokenPageMapper.toAppPage(entities);
    }

    @Override
    public AppPage<RefreshToken> findAllByGlobalUserId(GlobalUserId globalUserId, int page, int pageSize) {
        if (globalUserId == null) throw new NullPointerException("globalUserId is null");

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<RefreshTokenJpaEntity> entities = jpaRepositoryRefreshToken
                .findAllByGlobalUserId(pageable, globalUserId.asUUID());

        return refreshTokenPageMapper.toAppPage(entities);
    }

    @Override
    public void deleteAllByAppUserId(AppUserId appUserId) {
        if (appUserId == null) throw new NullPointerException("appUserId is null");

        jpaRepositoryRefreshToken.deleteByUserId(appUserId.asUUID());
    }

    @Override
    public void deleteAllByGlobalUserId(GlobalUserId globalUserId) {
        if (globalUserId == null) throw new NullPointerException("globalUserId is null");

        jpaRepositoryRefreshToken.deleteByGlobalUserId(globalUserId.asUUID());
    }

    @Override
    public void deleteById(RefreshTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("tokenId is null");

        jpaRepositoryRefreshToken.deleteById(tokenId.asUUID());
    }
}
