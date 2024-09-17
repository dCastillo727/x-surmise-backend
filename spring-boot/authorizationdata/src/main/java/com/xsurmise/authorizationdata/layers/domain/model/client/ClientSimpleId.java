package com.xsurmise.authorizationdata.layers.domain.model.client;

import java.util.UUID;

public record ClientSimpleId(String id) {
    public ClientSimpleId {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("id cannot be null or empty");
    }

    public static ClientSimpleId from(String id) {
        return new ClientSimpleId(id);
    }

    public static ClientSimpleId from(UUID id) {
        return new ClientSimpleId(id.toString());
    }

    public static ClientSimpleId generate() {
        return new ClientSimpleId(UUID.randomUUID().toString());
    }

    public UUID asUUID() {
        return UUID.fromString(id);
    }
}
