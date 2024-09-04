package com.xsurmise.authorizationdata.layers.domain.model.globaluser;

import java.util.UUID;

public record GlobalUserId(String id) {
    public GlobalUserId {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Invalid user id");
    }

    public static GlobalUserId from(final String id) {
        return new GlobalUserId(id);
    }

    public static GlobalUserId from(final UUID id) {
        return new GlobalUserId(id.toString());
    }

    public static GlobalUserId generate() {
        return new GlobalUserId(UUID.randomUUID().toString());
    }

    public String asString() {
        return id;
    }

    public UUID asUUID() {
        return UUID.fromString(id);
    }
}
