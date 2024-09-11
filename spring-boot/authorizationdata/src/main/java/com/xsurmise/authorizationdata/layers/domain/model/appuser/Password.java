package com.xsurmise.authorizationdata.layers.domain.model.appuser;

public record Password(String value) {
    public Password {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty");
    }

    public static Password from(String value) {
        return new Password(value);
    }
}
