package com.xsurmise.authorizationdata.layers.domain.model.appuser;

import com.xsurmise.authorizationdata.common.utils.aggregate.AggregateDomainEvent;
import com.xsurmise.authorizationdata.common.utils.event.DomainEvent;
import com.xsurmise.authorizationdata.layers.domain.event.appuser.AppUserDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.model.client.ClientSimpleId;
import com.xsurmise.authorizationdata.layers.domain.model.globaluser.GlobalUserId;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class AppUser implements AggregateDomainEvent {
    private final List<AppUserDomainEvent> events;
    private final AppUserId id;
    private final AppUserDate createdAt;
    private AppUserDate updatedAt;
    private final GlobalUserId globalUserId;
    private final ClientSimpleId clientId;
    private Username username;
    private AppUserEmailAddress emailAddress;
    private Password password;
    private boolean isLinked;

    public AppUser(
            AppUserId id,
            AppUserDate createdAt,
            AppUserDate updatedAt,
            GlobalUserId globalUserId,
            ClientSimpleId clientId,
            Username username,
            AppUserEmailAddress emailAddress,
            Password password,
            boolean isLinked
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(createdAt);
        Objects.requireNonNull(updatedAt);
        Objects.requireNonNull(globalUserId);
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(username);
        Objects.requireNonNull(emailAddress);
        Objects.requireNonNull(password);

        this.events = new ArrayList<>();
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.globalUserId = globalUserId;
        this.clientId = clientId;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isLinked = isLinked;
    }

    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public List<? extends DomainEvent> listEvents() {
        return List.copyOf(events);
    }
}
