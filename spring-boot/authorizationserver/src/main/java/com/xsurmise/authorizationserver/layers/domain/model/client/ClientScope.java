package com.xsurmise.authorizationserver.layers.domain.model.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientScope {
    READ("read"),
    WRITE("write"),
    ADMIN("admin")
    ;

    private final String value;

    public static ClientScope fromValue(String value) {
        for (ClientScope scope : ClientScope.values()) {
            if (scope.value.equals(value)) {
                return scope;
            }
        }

        throw new IllegalArgumentException("Invalid scope value: " + value);
    }
}