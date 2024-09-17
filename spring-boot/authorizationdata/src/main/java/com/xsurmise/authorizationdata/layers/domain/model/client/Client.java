package com.xsurmise.authorizationdata.layers.domain.model.client;

import com.xsurmise.authorizationdata.common.utils.aggregate.AggregateDomainEvent;
import com.xsurmise.authorizationdata.layers.domain.command.client.CreateClientCommand;
import com.xsurmise.authorizationdata.layers.domain.event.client.ClientCreatedEvent;
import com.xsurmise.authorizationdata.layers.domain.event.client.ClientDomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Client implements AggregateDomainEvent {
    private final List<ClientDomainEvent> events;
    private final ClientSimpleId id;
    private final ClientId clientId;
    private final ClientSecret clientSecret;
    private List<GrantType> grantTypes;
    private List<RedirectUri> redirectUris;
    private List<RedirectUri> postLogoutUris;
    private List<Scope> scopes;
    private TokenValidity accessTokenValidity;
    private TokenValidity refreshTokenValidity;
    private final ClientDate createdAt;
    private ClientDate updatedAt;

    public Client(
            ClientSimpleId id,
            ClientId clientId,
            ClientSecret clientSecret,
            List<GrantType> grantTypes,
            List<RedirectUri> redirectUris,
            List<RedirectUri> postLogoutUris,
            List<Scope> scopes,
            TokenValidity accessTokenValidity,
            TokenValidity refreshTokenValidity,
            ClientDate createdAt,
            ClientDate updatedAt
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(clientSecret);
        Objects.requireNonNull(grantTypes);
        Objects.requireNonNull(redirectUris);
        Objects.requireNonNull(postLogoutUris);
        Objects.requireNonNull(scopes);
        Objects.requireNonNull(accessTokenValidity);
        Objects.requireNonNull(refreshTokenValidity);
        Objects.requireNonNull(createdAt);
        Objects.requireNonNull(updatedAt);

        this.events = new ArrayList<>();
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantTypes = grantTypes;
        this.redirectUris = redirectUris;
        this.postLogoutUris = postLogoutUris;
        this.scopes = scopes;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Client createBy(final CreateClientCommand command) {
        if (command == null) throw new NullPointerException("command cannot be null");

        final ClientSimpleId simpleId = ClientSimpleId.generate();
        final Client client = new Client(
                simpleId,
                command.clientId(),
                command.clientSecret(),
                command.grantTypes(),
                command.redirectUris(),
                command.postLogoutUris(),
                command.scopes(),
                command.accessTokenValidity(),
                command.refreshTokenValidity(),
                ClientDate.now(),
                ClientDate.now()
        );

        client.events.add(ClientCreatedEvent.issue(simpleId));
        return client;
    }

    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public List<ClientDomainEvent> listEvents() {
        return List.copyOf(events);
    }
}
