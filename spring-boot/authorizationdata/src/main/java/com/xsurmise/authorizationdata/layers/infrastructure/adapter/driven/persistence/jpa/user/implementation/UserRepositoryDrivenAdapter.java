package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user.UserRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUser;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository.JpaRepositoryAppUser;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository.JpaRepositoryGlobalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class UserRepositoryDrivenAdapter implements UserRepositoryDrivenPort {
    private final JpaRepositoryGlobalUser jpaRepositoryGlobalUser;
    private final JpaRepositoryAppUser jpaRepositoryAppUser;

    @Override
    public Optional<GlobalUser> findByAppUserId(AppUserId appUserId) {
        return Optional.empty();
    }

    @Override
    public List<AppUser> findAllByGlobalUserId(GlobalUserId globalUserId) {
        return List.of();
    }

    @Override
    public void deleteAppUserByGlobalUserIdAndClientId(GlobalUserId globalUserId, ClientSimpleId clientSimpleId) {

    }
}
