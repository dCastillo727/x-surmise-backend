package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.layers.application.dto.utils.response.AppPage;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.accesstoken.AccessTokenRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessToken;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessTokenId;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.jparepository.JpaRepositoryAccessToken;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void save(AccessToken accessToken) {

    }

    @Override
    public Optional<AccessToken> findById(AccessTokenId tokenId) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(AccessTokenId tokenId) {
        return false;
    }

    @Override
    public AppPage<AccessToken> findAll(int page, int pageSize) {
        return null;
    }

    @Override
    public AppPage<AccessToken> findAllByAppUserId(AppUserId appUserId, int page, int pageSize) {
        return null;
    }

    @Override
    public AppPage<AccessToken> findAllByGlobalUserId(GlobalUserId globalUserId, int page, int pageSize) {
        return null;
    }

    @Override
    public void deleteAllByAppUserId(AppUserId appUserId) {

    }

    @Override
    public void deleteAllByGlobalUserId(GlobalUserId globalUserId) {

    }

    @Override
    public void deleteById(AccessTokenId tokenId) {

    }
}
