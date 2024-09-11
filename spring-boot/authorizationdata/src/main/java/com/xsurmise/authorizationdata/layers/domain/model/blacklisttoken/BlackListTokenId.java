package com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken;

import java.util.UUID;

public record BlackListTokenId(String value) {
    public BlackListTokenId {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("TokenId Value cannot be null or empty");
    }

    public static BlackListTokenId from(String value) {
        return new BlackListTokenId(value);
    }

    public static BlackListTokenId from(UUID value) {
        return new BlackListTokenId(value.toString());
    }

    public static BlackListTokenId generate() {
        UUID uuid = UUID.randomUUID();
        return new BlackListTokenId(uuid.toString());
    }

    public UUID asUUID() {
        return UUID.fromString(this.value);
    }
}
