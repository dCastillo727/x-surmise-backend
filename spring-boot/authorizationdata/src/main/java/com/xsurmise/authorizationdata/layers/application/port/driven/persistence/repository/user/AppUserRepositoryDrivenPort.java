package com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user;

import com.xsurmise.authorizationdata.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserEmailAddress;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.Username;

import java.util.Optional;

@DrivenPort
public interface AppUserRepositoryDrivenPort {
    void save(AppUser appUser);

    Optional<AppUser> findByUsername(Username username);

    Optional<AppUser> findByEmail(AppUserEmailAddress email);

    Optional<AppUser> findById(AppUserId appUserId);

    boolean existsByUsername(Username username);

    boolean existsByEmail(AppUserEmailAddress email);

    boolean existsById(AppUserId appUserId);

    AppUser update(AppUser appUser);

    void deleteById(AppUserId appUserId);
}
