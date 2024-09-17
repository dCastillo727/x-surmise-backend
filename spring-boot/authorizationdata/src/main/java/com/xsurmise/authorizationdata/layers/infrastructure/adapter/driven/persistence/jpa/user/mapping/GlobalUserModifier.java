package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.ModifierMapper;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUser;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity.GlobalUserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class GlobalUserModifier implements ModifierMapper<GlobalUser, GlobalUserJpaEntity> {
    private final GlobalUserJpaMapper mapper;

    private GlobalUser domainModel;

    @Override
    public GlobalUserModifier applyChangesFrom(GlobalUser origin) {
        return new GlobalUserModifier(mapper, origin);
    }

    @Override
    public GlobalUserJpaEntity to(GlobalUserJpaEntity end) {
        end.setId(domainModel.getId().asUUID());
        end.setEmail(domainModel.getEmail().value());
        end.setPhone(domainModel.getPhone().value());
        end.setRole(mapper.toRoleEntity(domainModel.getRole()));
        end.setLastLogInAt(end.getLastLogInAt());

        return end;
    }
}
