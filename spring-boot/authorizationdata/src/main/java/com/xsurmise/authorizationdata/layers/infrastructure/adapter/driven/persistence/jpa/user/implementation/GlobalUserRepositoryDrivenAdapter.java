package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.implementation;

import com.xsurmise.authorizationdata.common.utils.annotations.adapter.DrivenAdapter;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.common.utils.mapping.ModifierMapper;
import com.xsurmise.authorizationdata.layers.application.port.driven.persistence.repository.user.GlobalUserRepositoryDrivenPort;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUser;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserEmailAddress;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.PhoneNumber;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.GlobalUserJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.jparepository.JpaRepositoryGlobalUser;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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

    private final MapperEntity<GlobalUser, GlobalUserJpaEntity> globalUserMapper;
    private final ModifierMapper<GlobalUser, GlobalUserJpaEntity> globalUserModifier;

    @Override
    public void save(GlobalUser globalUser) {
        if (globalUser == null) throw new NullPointerException("globalUser is null");

        if (jpaRepositoryGlobalUser.existsById(globalUser.getId().asUUID()))
            throw new EntityExistsException("globalUser is already exists");

        GlobalUserJpaEntity entity = globalUserMapper.toEntity(globalUser);

        jpaRepositoryGlobalUser.save(entity);
    }

    @Override
    public Optional<GlobalUser> findByEmailAddress(GlobalUserEmailAddress email) {
        if (email == null) throw new NullPointerException("email is null");

        return jpaRepositoryGlobalUser.findByEmail(email.value()).map(globalUserMapper::toDomainModel);
    }

    @Override
    public Optional<GlobalUser> findByPhoneNumber(PhoneNumber phoneNumber) {
        if (phoneNumber == null) throw new NullPointerException("phoneNumber is null");

        return jpaRepositoryGlobalUser.findByPhone(phoneNumber.value()).map(globalUserMapper::toDomainModel);
    }

    @Override
    public Optional<GlobalUser> findById(GlobalUserId id) {
        if (id == null) throw new NullPointerException("id is null");

        return jpaRepositoryGlobalUser.findById(id.asUUID()).map(globalUserMapper::toDomainModel);
    }

    @Override
    public boolean existsByEmailAddress(GlobalUserEmailAddress email) {
        if (email == null) throw new NullPointerException("email is null");

        return jpaRepositoryGlobalUser.existsByEmail(email.value());
    }

    @Override
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        if (phoneNumber == null) throw new NullPointerException("phoneNumber is null");

        return jpaRepositoryGlobalUser.existsByPhone(phoneNumber.value());
    }

    @Override
    public boolean existsById(GlobalUserId id) {
        if (id == null) throw new NullPointerException("id is null");

        return jpaRepositoryGlobalUser.existsById(id.asUUID());
    }

    @Override
    public GlobalUser update(GlobalUser globalUser) {
        if (globalUser == null) throw new NullPointerException("globalUser is null");

        GlobalUserJpaEntity foundEntity = jpaRepositoryGlobalUser
                .findById(globalUser.getId().asUUID())
                .orElseThrow(() -> new EntityNotFoundException("globalUser not found"));

        GlobalUserJpaEntity modifiedEntity = globalUserModifier.applyChangesFrom(globalUser).to(foundEntity);
        jpaRepositoryGlobalUser.save(modifiedEntity);

        return globalUser;
    }

    @Override
    public void deleteById(GlobalUserId id) {
        if (id == null) throw new NullPointerException("id is null");

        jpaRepositoryGlobalUser.deleteById(id.asUUID());
    }
}
