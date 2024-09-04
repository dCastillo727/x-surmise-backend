package com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user;

import com.xsurmise.authorizationdata.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUser;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;

import java.util.List;
import java.util.Optional;

@DrivenPort
public interface UserRepositoryDrivenPort {
    Optional<GlobalUser> findByAppUserId(AppUserId appUserId);

    List<AppUser> findAllByGlobalUserId(GlobalUserId globalUserId);

    void deleteAppUserByGlobalUserIdAndClientId(GlobalUserId globalUserId, ClientSimpleId clientSimpleId);
}
