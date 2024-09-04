package com.xsurmise.authorizationdata.layers.domain.model.client;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GrantType {
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit")
    ;

    private final String value;
}
