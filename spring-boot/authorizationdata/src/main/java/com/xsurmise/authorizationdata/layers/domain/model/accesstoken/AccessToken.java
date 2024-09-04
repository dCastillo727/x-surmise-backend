package com.xsurmise.authorizationdata.layers.domain.model.accesstoken;

import com.xsurmise.authorizationdata.common.utils.aggregate.AggregateDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.event.accesstoken.AccessTokenDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.model.appuser.AppUserId;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientId;
import com.xsurmise.authorizationdata.layers.domain.model.client.Scope;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class AccessToken implements AggregateDomainEvent {
    private final List<AccessTokenDomainEvent> events;
    private final AccessTokenId tokenId;
    private final AppUserId userId;
    private final ClientId clientId;
    private List<Scope> scopes;
    private AccessTokenDate issuedAt;
    private AccessTokenDate expiresAt;

    public AccessToken(
            AccessTokenId tokenId,
            AppUserId userId,
            ClientId clientId,
            List<Scope> scopes,
            AccessTokenDate issuedAt,
            AccessTokenDate expiresAt
    ) {
        Objects.requireNonNull(tokenId);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(scopes);
        Objects.requireNonNull(issuedAt);
        Objects.requireNonNull(expiresAt);

        this.events = new ArrayList<>();
        this.tokenId = tokenId;
        this.userId = userId;
        this.clientId = clientId;
        this.scopes = scopes;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public List<AccessTokenDomainEvent> listEvents() {
        return List.copyOf(events);
    }
}
