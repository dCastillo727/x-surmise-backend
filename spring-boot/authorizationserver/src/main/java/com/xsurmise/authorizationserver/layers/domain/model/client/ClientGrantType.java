package com.xsurmise.authorizationserver.layers.domain.model.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientGrantType {
    DEVICE_CODE("device_code"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code"),
    JWT_BEARER("jwt_bearer"),
    TOKEN_EXCHANGE("token_exchange")
    ;

    private final String value;

    public static ClientGrantType fromValue(String value) {
        for (ClientGrantType type : ClientGrantType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid GrantType value: " + value);
    }
}
