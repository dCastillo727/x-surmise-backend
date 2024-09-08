package com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.refreshtoken;

import com.xsurmise.authorizationdata.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationdata.layers.application.dto.utils.response.pagination.AppPage;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshToken;
import com.xsurmise.authorizationdata.layers.domain.model.refreshtoken.RefreshTokenId;

@DrivenPort
public interface RefreshTokenRepositoryDrivenPort {
    void save(RefreshToken refreshToken);

    RefreshToken findById(RefreshTokenId tokenId);

    boolean existsById(RefreshTokenId tokenId);

    AppPage<RefreshToken> findAll(int page, int pageSize);

    AppPage<RefreshToken> findAllByAppUserId(AppUserId appUserId, int page, int pageSize);

    AppPage<RefreshToken> findAllByGlobalUserId(GlobalUserId globalUserId, int page, int pageSize);

    void deleteAllByAppUserId(AppUserId appUserId);

    void deleteAllByGlobalUserId(GlobalUserId globalUserId);

    void deleteById(RefreshTokenId tokenId);
}
