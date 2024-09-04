package com.xsurmise.authorizationdata.layers.domain.model.blacklisttoken;

import com.xsurmise.authorizationdata.common.utils.aggregate.AggregateDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.event.blacklisttoken.BlackListTokenDomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class BlackListToken implements AggregateDomainEvent {
    private final List<BlackListTokenDomainEvent> events;
    private final BlackListTokenId tokenId;
    private BlackListTokenDate revokedAt;

    public BlackListToken(
            BlackListTokenId tokenId,
            BlackListTokenDate revokedAt
    ) {
        Objects.requireNonNull(tokenId);
        Objects.requireNonNull(revokedAt);

        this.events = new ArrayList<>();
        this.tokenId = tokenId;
        this.revokedAt = revokedAt;
    }

    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public List<BlackListTokenDomainEvent> listEvents() {
        return List.copyOf(events);
    }
}
