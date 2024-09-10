package com.xsurmise.authorizationdata.layers.domain.model.client;

public record ClientSecret(String value) {
    public ClientSecret {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("Client secret value cannot be null or empty");
    }

    public static ClientSecret from(String value) {
        return new ClientSecret(value);
    }
}
