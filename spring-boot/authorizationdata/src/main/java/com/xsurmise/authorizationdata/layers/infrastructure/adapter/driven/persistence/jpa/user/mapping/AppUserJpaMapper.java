package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.MapperEntity;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.*;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.AppUserJpaEntity;

public class AppUserJpaMapper implements MapperEntity<AppUser, AppUserJpaEntity> {
    @Override
    public AppUserJpaEntity toEntity(AppUser domainModel) {
        return AppUserJpaEntity.builder()
                .id(domainModel.getId().asUUID())
                .createdAt(domainModel.getCreatedAt().toOffsetDateTime())
                .updatedAt(domainModel.getUpdatedAt().toOffsetDateTime())
                .globalUserId(domainModel.getGlobalUserId().asUUID())
                .clientId(domainModel.getClientId().id())
                .username(domainModel.getUsername().value())
                .email(domainModel.getEmailAddress().value())
                .encryptedPassword(domainModel.getPassword().value())
                .isLinked(domainModel.isLinked())
                .build();
    }

    @Override
    public AppUser toDomainModel(AppUserJpaEntity entity) {
        return new AppUser(
                AppUserId.from(entity.getId()),
                AppUserDate.from(entity.getCreatedAt()),
                AppUserDate.from(entity.getUpdatedAt()),
                GlobalUserId.from(entity.getGlobalUserId()),
                ClientSimpleId.from(entity.getClientId()),
                Username.from(entity.getUsername()),
                AppUserEmailAddress.from(entity.getEmail()),
                Password.from(entity.getEncryptedPassword()),
                entity.getIsLinked()
        );
    }
}
