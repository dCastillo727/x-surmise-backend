package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum RoleJpaEntity {
    USER("user"),
    ADMIN("admin"),
    MANAGER("manager");

    private final String value;

    public static RoleJpaEntity fromValue(String value) {
        Objects.requireNonNull(value);

        for (RoleJpaEntity role : RoleJpaEntity.values()) {
            if (role.value.equalsIgnoreCase(value))
                return role;
        }

        throw new IllegalArgumentException("Invalid role value: " + value);
    }
}
