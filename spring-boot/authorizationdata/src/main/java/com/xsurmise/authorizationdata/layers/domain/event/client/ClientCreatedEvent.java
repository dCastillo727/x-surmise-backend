package com.xsurmise.authorizationdata.layers.domain.event.client;

import com.xsurmise.authorizationdata.common.utils.event.EventId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;

import java.time.Instant;

public record ClientCreatedEvent(
        EventId eventId,
        Instant registeredAt,
        ClientSimpleId clientSimpleId
) implements ClientDomainEvent {
    public static ClientCreatedEvent issue(final ClientSimpleId id) {
        return new ClientCreatedEvent(EventId.generate(), Instant.now(), id);
    }

    @Override
    public EventId getId() {
        return eventId;
    }
}
