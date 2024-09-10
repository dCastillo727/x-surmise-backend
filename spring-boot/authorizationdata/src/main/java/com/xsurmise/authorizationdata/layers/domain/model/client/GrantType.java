package com.xsurmise.authorizationdata.layers.domain.model.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GrantType {
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit")
    ;

    private final String value;

    public static GrantType fromValue(String value) {
        for (GrantType type : GrantType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid GrantType value: " + value);
    }
}
