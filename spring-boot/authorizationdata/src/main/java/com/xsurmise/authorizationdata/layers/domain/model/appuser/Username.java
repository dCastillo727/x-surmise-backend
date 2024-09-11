package com.xsurmise.authorizationdata.layers.domain.model.appuser;

public record Username(String value) {
    public Username {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Username cannot be null or empty");
    }

    public static Username from(String value) {
        return new Username(value);
    }
}
