package com.xsurmise.authorizationdata.layers.domain.model.globaluser.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

import static com.xsurmise.authorizationdata.layers.domain.model.globaluser.role.UserPermission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER(Collections.emptySet()),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_CREATE,
            ADMIN_UPDATE,
            ADMIN_DELETE
    )),
    MANAGER(Set.of(
            MANAGER_READ,
            MANAGER_CREATE,
            MANAGER_UPDATE,
            MANAGER_DELETE
    ))

    ;
    private final Set<UserPermission> permissions;
}
