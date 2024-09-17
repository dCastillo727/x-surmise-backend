package com.xsurmise.authorizationserver.layers.domain.event.client;

import com.xsurmise.authorizationserver.common.utils.event.EventId;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientId;

import java.time.Instant;

public record ClientCreatedEvent(EventId eventId, Instant registeredAt, ClientId clientId) implements ClientDomainEvent{
    public static ClientCreatedEvent issue(ClientId clientId) {
        return new ClientCreatedEvent(EventId.generate(), Instant.now(), clientId);
    }

    @Override
    public EventId getId() {
        return eventId;
    }
}
