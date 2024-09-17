package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user.UserRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUser;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.AppUserJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.GlobalUserJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository.JpaRepositoryAppUser;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository.JpaRepositoryGlobalUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class UserRepositoryDrivenAdapter implements UserRepositoryDrivenPort {
    private final JpaRepositoryGlobalUser jpaRepositoryGlobalUser;
    private final JpaRepositoryAppUser jpaRepositoryAppUser;

    private final MapperEntity<GlobalUser, GlobalUserJpaEntity> globalUserMapper;
    private final MapperEntity<AppUser, AppUserJpaEntity> appUserMapper;

    @Override
    public Optional<GlobalUser> findByAppUserId(AppUserId appUserId) {
        if (appUserId == null) throw new NullPointerException("appUserId is null");

        Optional<AppUserJpaEntity> appUserEntity = jpaRepositoryAppUser.findById(appUserId.asUUID());

        return appUserEntity.map(
                appUserJpaEntity -> globalUserMapper.toDomainModel(appUserJpaEntity.getGlobalUser())
        );

    }

    @Override
    public List<AppUser> findAllByGlobalUserId(GlobalUserId globalUserId) {
        if (globalUserId == null) throw new NullPointerException("globalUserId is null");

        Optional<GlobalUserJpaEntity> globalUserEntity = jpaRepositoryGlobalUser.findById(globalUserId.asUUID());

        return globalUserEntity.map(
                globalUserJpaEntity -> globalUserJpaEntity.getAppUsers().stream()
                        .map(appUserMapper::toDomainModel).toList()
        ).orElseThrow(() -> new EntityNotFoundException("Could not find global user"));
    }

    @Override
    public void deleteAppUserByGlobalUserIdAndClientId(GlobalUserId globalUserId, ClientSimpleId clientSimpleId) {
        if (globalUserId == null || clientSimpleId == null)
            throw new NullPointerException("globalUserId or clientSimpleId is null");

        Optional<GlobalUserJpaEntity> globalUserEntity = jpaRepositoryGlobalUser
                .findById(globalUserId.asUUID());

        if (globalUserEntity.isEmpty()) throw new EntityNotFoundException("Could not find global user");

        for (AppUserJpaEntity appUser : globalUserEntity.get().getAppUsers()) {
            if (Objects.equals(appUser.getClientId(), clientSimpleId.asUUID())) {
                jpaRepositoryAppUser.delete(appUser);
                return;
            }
        }

        throw new EntityNotFoundException("Could not find app user of requested client");
    }
}
