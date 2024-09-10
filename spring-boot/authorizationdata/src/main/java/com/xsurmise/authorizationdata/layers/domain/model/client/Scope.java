package com.xsurmise.authorizationdata.layers.domain.model.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Scope {
    READ("read"),
    WRITE("write"),
    ADMIN("admin")
    ;

    private final String value;

    public static Scope fromValue(String value) {
        for (Scope scope : Scope.values()) {
            if (scope.value.equals(value)) {
                return scope;
            }
        }

        throw new IllegalArgumentException("Invalid scope value: " + value);
    }
}
