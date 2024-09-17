package com.xsurmise.authorizationdata.layers.domain.model.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GrantType {
    DEVICE_CODE("device_code"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code"),
    JWT_BEARER("jwt_bearer"),
    TOKEN_EXCHANGE("token_exchange")
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
