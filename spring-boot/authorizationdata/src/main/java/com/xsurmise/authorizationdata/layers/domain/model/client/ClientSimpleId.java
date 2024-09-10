package com.xsurmise.authorizationdata.layers.domain.model.client;

public record ClientSimpleId(Integer id) {
    public ClientSimpleId {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
    }

    public static ClientSimpleId from(Integer id) {
        return new ClientSimpleId(id);
    }
}
