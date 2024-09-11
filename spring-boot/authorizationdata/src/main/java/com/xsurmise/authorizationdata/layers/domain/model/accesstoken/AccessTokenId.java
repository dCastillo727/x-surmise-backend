package com.xsurmise.authorizationdata.layers.domain.model.accesstoken;

import java.util.UUID;

public record AccessTokenId(String value) {
    public AccessTokenId {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("TokenId Value cannot be null or empty");
    }

    public static AccessTokenId from(String value) {
        return new AccessTokenId(value);
    }

    public static AccessTokenId from(UUID value) {
        return new AccessTokenId(value.toString());
    }

    public static AccessTokenId generate() {
        UUID uuid = UUID.randomUUID();
        return new AccessTokenId(uuid.toString());
    }

    public UUID asUUID() {
        return UUID.fromString(this.value);
    }
}
