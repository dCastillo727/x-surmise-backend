package com.xsurmise.authorizationserver.layers.domain.model.client;

import java.util.UUID;

public record ClientSimpleId(String value) {
    public ClientSimpleId {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException("ClientSimpleId cannot be null or empty");
    }

    public static ClientSimpleId from(String value) {
        return new ClientSimpleId(value);
    }

    public static ClientSimpleId from(UUID clientSimpleId) {
        return new ClientSimpleId(clientSimpleId.toString());
    }

    public static ClientSimpleId generate() {
        return new ClientSimpleId(UUID.randomUUID().toString());
    }

    public UUID asUUID() {
        return UUID.fromString(value);
    }
}
