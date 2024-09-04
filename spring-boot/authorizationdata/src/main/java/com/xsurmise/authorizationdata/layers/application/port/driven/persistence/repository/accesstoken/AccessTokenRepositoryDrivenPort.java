package com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.accesstoken;

import com.xsurmise.authorizationdata.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationdata.layers.application.dto.utils.response.AppPage;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessToken;
import com.xsurmise.authorizationdata.layers.domain.model.accesstoken.AccessTokenId;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;

import java.util.Optional;

@DrivenPort
public interface AccessTokenRepositoryDrivenPort {
    void save(AccessToken accessToken);

    Optional<AccessToken> findById(AccessTokenId tokenId);

    boolean existsById(AccessTokenId tokenId);

    AppPage<AccessToken> findAll(int page, int pageSize);

    AppPage<AccessToken> findAllByAppUserId(AppUserId appUserId, int page, int pageSize);

    AppPage<AccessToken> findAllByGlobalUserId(GlobalUserId globalUserId, int page, int pageSize);

    void deleteAllByAppUserId(AppUserId appUserId);

    void deleteAllByGlobalUserId(GlobalUserId globalUserId);

    void deleteById(AccessTokenId tokenId);
}
