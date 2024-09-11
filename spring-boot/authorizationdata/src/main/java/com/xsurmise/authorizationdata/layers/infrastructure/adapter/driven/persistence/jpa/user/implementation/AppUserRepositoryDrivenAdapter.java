package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user.AppUserRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserEmailAddress;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.Username;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.AppUserJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository.JpaRepositoryAppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class AppUserRepositoryDrivenAdapter implements AppUserRepositoryDrivenPort {
    private final JpaRepositoryAppUser jpaRepositoryAppUser;

    private final MapperEntity<AppUser, AppUserJpaEntity> appUserJpaMapper;

    @Override
    public void save(AppUser appUser) {
        if (appUser == null) throw new NullPointerException("appUser cannot be null");

        AppUserJpaEntity entity = appUserJpaMapper.toEntity(appUser);

        jpaRepositoryAppUser.save(entity);
    }

    @Override
    public Optional<AppUser> findByUsername(Username username) {
        if (username == null) throw new NullPointerException("username cannot be null");

        return jpaRepositoryAppUser.findByUsername(username.value()).map(appUserJpaMapper::toDomainModel);
    }

    @Override
    public Optional<AppUser> findByEmail(AppUserEmailAddress email) {
        if (email == null) throw new NullPointerException("email cannot be null");

        return jpaRepositoryAppUser.findByEmail(email.value()).map(appUserJpaMapper::toDomainModel);
    }

    @Override
    public Optional<AppUser> findById(AppUserId appUserId) {
        if (appUserId == null) throw new NullPointerException("appUserId cannot be null");

        return jpaRepositoryAppUser.findById(appUserId.asUUID()).map(appUserJpaMapper::toDomainModel);
    }

    @Override
    public boolean existsByUsername(Username username) {
        if (username == null) throw new NullPointerException("Username cannot be null");

        return jpaRepositoryAppUser.existsByUsername(username.value());
    }

    @Override
    public boolean existsByEmail(AppUserEmailAddress email) {
        if (email == null) throw new NullPointerException("Email cannot be null");

        return jpaRepositoryAppUser.existsByEmail(email.value());
    }

    @Override
    public boolean existsById(AppUserId appUserId) {
        if (appUserId == null) throw new NullPointerException("AppUserId cannot be null");

        return jpaRepositoryAppUser.existsById(appUserId.asUUID());
    }

    @Override
    public AppUser update(AppUser appUser) {
        if (appUser == null) throw new NullPointerException("appUser cannot be null");

        AppUserJpaEntity entity = appUserJpaMapper.toEntity(appUser);
        jpaRepositoryAppUser.save(entity);

        return appUser;
    }

    @Override
    public void deleteById(AppUserId appUserId) {
        if (appUserId == null) throw new NullPointerException("appUserId cannot be null");

        jpaRepositoryAppUser.deleteById(appUserId.asUUID());
    }
}
