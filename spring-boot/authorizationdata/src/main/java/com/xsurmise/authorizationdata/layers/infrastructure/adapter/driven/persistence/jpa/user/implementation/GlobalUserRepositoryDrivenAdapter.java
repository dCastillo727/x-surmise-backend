package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user.GlobalUserRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUser;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserEmailAddress;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.PhoneNumber;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository.JpaRepositoryGlobalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
@DrivenAdapter
@RequiredArgsConstructor
public class GlobalUserRepositoryDrivenAdapter implements GlobalUserRepositoryDrivenPort {
    private final JpaRepositoryGlobalUser jpaRepositoryGlobalUser;

    @Override
    public void save(GlobalUser globalUser) {

    }

    @Override
    public Optional<GlobalUser> findByEmailAddress(GlobalUserEmailAddress email) {
        return Optional.empty();
    }

    @Override
    public Optional<GlobalUser> findByPhoneNumber(PhoneNumber phoneNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<GlobalUser> findById(GlobalUserId id) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmailAddress(GlobalUserEmailAddress email) {
        return false;
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return false;
    }

    @Override
    public boolean existsById(GlobalUserId id) {
        return false;
    }

    @Override
    public GlobalUser update(GlobalUser globalUser) {
        return null;
    }

    @Override
    public void deleteById(GlobalUserId id) {

    }
}
