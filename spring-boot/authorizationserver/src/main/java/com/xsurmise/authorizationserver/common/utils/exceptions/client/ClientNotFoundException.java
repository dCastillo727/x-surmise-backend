package com.xsurmise.authorizationserver.common.utils.exceptions.client;

import com.xsurmise.authorizationserver.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientSimpleId;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(ClientSimpleId id) {
        super("Client with id " + id + " not found");
    }

    public ClientNotFoundException(ClientId clientId) {
        super("Client with ClientId " + clientId + " not found");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
