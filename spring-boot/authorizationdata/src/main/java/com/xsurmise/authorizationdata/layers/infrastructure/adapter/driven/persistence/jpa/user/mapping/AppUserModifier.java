package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.ModifierMapper;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUser;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.AppUserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AppUserModifier implements ModifierMapper<AppUser, AppUserJpaEntity> {
    private AppUser appUser;

    @Override
    public AppUserModifier applyChangesFrom(AppUser origin) {
        return new AppUserModifier(origin);
    }

    @Override
    public AppUserJpaEntity to(AppUserJpaEntity end) {
        end.setId(appUser.getId().asUUID());
        end.setUsername(appUser.getUsername().value());
        end.setGlobalUserId(appUser.getGlobalUserId().asUUID());
        end.setClientId(appUser.getClientId().asUUID());
        end.setEmail(appUser.getEmailAddress().value());
        end.setEncryptedPassword(appUser.getPassword().value());
        end.setIsLinked(appUser.isLinked());

        return end;
    }
}
