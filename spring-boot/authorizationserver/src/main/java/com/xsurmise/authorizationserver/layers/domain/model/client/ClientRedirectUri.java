package com.xsurmise.authorizationserver.layers.domain.model.client;

public record ClientRedirectUri(String value) {
    public ClientRedirectUri {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Value cannot be null or empty");
    }

    public static ClientRedirectUri from(String value) {
        return new ClientRedirectUri(value);
    }
}
