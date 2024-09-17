package com.xsurmise.authorizationserver.layers.domain.model.client;

public record ClientTokenValidity(Integer value) {
    public ClientTokenValidity {
        if (value == null) throw new IllegalArgumentException("Value cannot be null");
    }

    public static ClientTokenValidity of(Integer value) {
        return new ClientTokenValidity(value);
    }
}
