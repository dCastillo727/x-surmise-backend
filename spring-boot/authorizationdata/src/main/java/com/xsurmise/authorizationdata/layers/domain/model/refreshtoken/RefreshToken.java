package com.xsurmise.authorizationdata.layers.domain.model.refreshtoken;

import com.xsurmise.authorizationdata.common.utils.aggregate.AggregateDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.event.refreshtoken.RefreshTokenDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class RefreshToken implements AggregateDomainEvent {
    private final List<RefreshTokenDomainEvent> events;
    private final AppUserId userId;
    private final ClientId clientId;
    private RefreshTokenDate issuedAt;
    private RefreshTokenDate expiresAt;

    public RefreshToken(
            AppUserId globalUserId,
            ClientId clientId,
            RefreshTokenDate issuedAt,
            RefreshTokenDate expiresAt
    ) {
        Objects.requireNonNull(globalUserId);
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(issuedAt);
        Objects.requireNonNull(expiresAt);

        this.events = new ArrayList<>();
        this.userId = globalUserId;
        this.clientId = clientId;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public List<RefreshTokenDomainEvent> listEvents() {
        return List.copyOf(events);
    }
}
