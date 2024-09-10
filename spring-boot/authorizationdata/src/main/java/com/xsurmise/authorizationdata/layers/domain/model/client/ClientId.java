package com.xsurmise.authorizationdata.layers.domain.model.client;

public record ClientId(String value) {
    public ClientId {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Client id cannot be null or empty");
    }

    public static ClientId from(String value) {
        return new ClientId(value);
    }
}
