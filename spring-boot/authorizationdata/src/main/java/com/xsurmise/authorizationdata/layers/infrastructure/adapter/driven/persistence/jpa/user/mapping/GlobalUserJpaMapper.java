package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.*;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.role.UserRole;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.GlobalUserJpaEntity;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.RoleJpaEntity;

public class GlobalUserJpaMapper implements MapperEntity<GlobalUser, GlobalUserJpaEntity> {
    @Override
    public GlobalUserJpaEntity toEntity(GlobalUser domainModel) {
        return GlobalUserJpaEntity.builder()
                .id(domainModel.getId().asUUID())
                .createdAt(domainModel.getCreatedAt().toOffsetDateTime())
                .email(domainModel.getEmail().value())
                .phone(domainModel.getPhone().value())
                .updatedAt(domainModel.getUpdatedAt().toOffsetDateTime())
                .role(toRoleEntity(domainModel.getRole()))
                .lastLogInAt(domainModel.getLastLogInAt().toOffsetDateTime())
                .build();
    }

    @Override
    public GlobalUser toDomainModel(GlobalUserJpaEntity entity) {
        return new GlobalUser(
                GlobalUserId.from(entity.getId()),
                GlobalUserDate.from(entity.getCreatedAt()),
                GlobalUserEmailAddress.from(entity.getEmail()),
                PhoneNumber.from(entity.getPhone()),
                GlobalUserDate.from(entity.getUpdatedAt()),
                toDomainRole(entity.getRole()),
                GlobalUserDate.from(entity.getLastLogInAt())
        );
    }

    public RoleJpaEntity toRoleEntity(UserRole domainModel) {
        return switch (domainModel) {
            case ADMIN -> RoleJpaEntity.ADMIN;
            case USER -> RoleJpaEntity.USER;
            case MANAGER -> RoleJpaEntity.MANAGER;
        };
    }

    public UserRole toDomainRole(RoleJpaEntity entity) {
        return switch (entity) {
            case ADMIN -> UserRole.ADMIN;
            case USER -> UserRole.USER;
            case MANAGER -> UserRole.MANAGER;
        };
    }
}
