package com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user;

import com.xsurmise.authorizationdata.common.utils.annotations.port.DrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUser;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserEmailAddress;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.PhoneNumber;

import java.util.Optional;

@DrivenPort
public interface GlobalUserRepositoryDrivenPort {
    void save(GlobalUser globalUser);

    Optional<GlobalUser> findByEmailAddress(GlobalUserEmailAddress email);

    Optional<GlobalUser> findByPhoneNumber(PhoneNumber phoneNumber);

    Optional<GlobalUser> findById(GlobalUserId id);

    boolean existsByEmailAddress(GlobalUserEmailAddress email);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    boolean existsById(GlobalUserId id);

    GlobalUser update(GlobalUser globalUser);

    void deleteById(GlobalUserId id);
}
