package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.application.dto.utils.response.pagination.AppPage;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.accesstoken.AccessTokenRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessToken;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessTokenId;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.entity.AccessTokenJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.jparepository.JpaRepositoryAccessToken;
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
public class AccessTokenRepositoryDrivenAdapter implements AccessTokenRepositoryDrivenPort {
    private final JpaRepositoryAccessToken jpaRepositoryAccessToken;

    private final MapperEntity<AccessToken, AccessTokenJpaEntity> accessTokenEntityMapper;
    private final PageMapper<AccessToken, AccessTokenJpaEntity> accessTokenPageMapper;

    @Override
    public void save(AccessToken accessToken) {
        if (accessToken == null) throw new NullPointerException("Access token is null");

        if (jpaRepositoryAccessToken.existsById(accessToken.getTokenId().asUUID()))
            throw new IllegalArgumentException("Access token already exists");

        AccessTokenJpaEntity entity = accessTokenEntityMapper.toEntity(accessToken);

        jpaRepositoryAccessToken.save(entity);
    }

    @Override
    public Optional<AccessToken> findById(AccessTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("Access token id is null");

        return jpaRepositoryAccessToken.findById(tokenId.asUUID()).map(accessTokenEntityMapper::toDomainModel);
    }

    @Override
    public boolean existsById(AccessTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("Access token id is null");

        return jpaRepositoryAccessToken.existsById(tokenId.asUUID());
    }

    @Override
    public AppPage<AccessToken> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AccessTokenJpaEntity> entities = jpaRepositoryAccessToken.findAll(pageable);

        return accessTokenPageMapper.toAppPage(entities);
    }

    @Override
    public AppPage<AccessToken> findAllByAppUserId(AppUserId appUserId, int page, int pageSize) {
        if (appUserId == null) throw new NullPointerException("App user id is null");

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AccessTokenJpaEntity> entities = jpaRepositoryAccessToken.findAllByUserId(pageable, appUserId.asUUID());

        return accessTokenPageMapper.toAppPage(entities);
    }

    @Override
    public AppPage<AccessToken> findAllByGlobalUserId(GlobalUserId globalUserId, int page, int pageSize) {
        if (globalUserId == null) throw new NullPointerException("Global user id is null");

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AccessTokenJpaEntity> entities = jpaRepositoryAccessToken
                .findAllByGlobalUserId(pageable, globalUserId.asUUID());

        return accessTokenPageMapper.toAppPage(entities);
    }

    @Override
    public void deleteAllByAppUserId(AppUserId appUserId) {
        if (appUserId == null) throw new NullPointerException("App user id is null");

        jpaRepositoryAccessToken.deleteByUserId(appUserId.asUUID());
    }

    @Override
    public void deleteAllByGlobalUserId(GlobalUserId globalUserId) {
        if (globalUserId == null) throw new NullPointerException("Global user id is null");

        jpaRepositoryAccessToken.deleteByGlobalUserId(globalUserId.asUUID());
    }

    @Override
    public void deleteById(AccessTokenId tokenId) {
        if (tokenId == null) throw new NullPointerException("Access token id is null");

        jpaRepositoryAccessToken.deleteById(tokenId.asUUID());
    }
}
