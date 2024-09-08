package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user.AppUserRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserEmailAddress;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.Username;
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

    @Override
    public void save(AppUser appUser) {

    }

    @Override
    public Optional<AppUser> findByUsername(Username username) {
        return Optional.empty();
    }

    @Override
    public Optional<AppUser> findByEmail(AppUserEmailAddress email) {
        return Optional.empty();
    }

    @Override
    public Optional<AppUser> findById(AppUserId appUserId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUsername(Username username) {
        return false;
    }

    @Override
    public boolean existsByEmail(AppUserEmailAddress email) {
        return false;
    }

    @Override
    public boolean existsById(AppUserId appUserId) {
        return false;
    }

    @Override
    public AppUser update(AppUser appUser) {
        return null;
    }

    @Override
    public void deleteById(AppUserId appUserId) {

    }
}
