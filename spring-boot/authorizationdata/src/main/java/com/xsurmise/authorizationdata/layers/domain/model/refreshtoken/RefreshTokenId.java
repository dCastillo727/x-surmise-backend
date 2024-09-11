package com.xsurmise.authorizationdata.layers.domain.model.refreshtoken;

import java.util.UUID;

public record RefreshTokenId(String value) {
    public RefreshTokenId {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("TokenId cannot be null or empty");
    }

    public static RefreshTokenId from(String value) {
        return new RefreshTokenId(value);
    }

    public static RefreshTokenId from(UUID value) {
        return new RefreshTokenId(value.toString());
    }

    public static RefreshTokenId generate() {
        return new RefreshTokenId(UUID.randomUUID().toString());
    }

    public UUID asUUID() {
        return UUID.fromString(this.value);
    }
}
