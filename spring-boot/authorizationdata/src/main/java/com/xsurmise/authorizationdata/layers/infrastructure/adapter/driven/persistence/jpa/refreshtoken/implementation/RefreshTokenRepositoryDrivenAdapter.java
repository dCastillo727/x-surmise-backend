package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.layers.application.dto.utils.response.AppPage;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.refreshtoken.RefreshTokenRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshToken;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshTokenId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.jparepository.JpaRepositoryRefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class RefreshTokenRepositoryDrivenAdapter implements RefreshTokenRepositoryDrivenPort {
    private final JpaRepositoryRefreshToken jpaRepositoryRefreshToken;

    @Override
    public void save(RefreshToken refreshToken) {

    }

    @Override
    public RefreshToken findById(RefreshTokenId tokenId) {
        return null;
    }

    @Override
    public boolean existsById(RefreshTokenId tokenId) {
        return false;
    }

    @Override
    public AppPage<RefreshToken> findAll(int page, int pageSize) {
        return null;
    }

    @Override
    public AppPage<RefreshToken> findAllByAppUserId(AppUserId appUserId, int page, int pageSize) {
        return null;
    }

    @Override
    public AppPage<RefreshToken> findAllByGlobalUserId(GlobalUserId globalUserId, int page, int pageSize) {
        return null;
    }

    @Override
    public void deleteAllByAppUserId(AppUserId appUserId) {

    }

    @Override
    public void deleteAllByGlobalUserId(GlobalUserId globalUserId) {

    }

    @Override
    public void deleteById(RefreshTokenId tokenId) {

    }
}
