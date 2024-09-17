package com.xsurmise.authorizationserver.layers.domain.model.client;

import com.xsurmise.authorizationserver.common.utils.aggregate.AggregateDomainEvent;
import com.xsurmise.authorizationserver.layers.domain.command.client.ClientCreateCommand;
import com.xsurmise.authorizationserver.layers.domain.event.client.ClientCreatedEvent;
import com.xsurmise.authorizationserver.layers.domain.event.client.ClientDomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Client implements AggregateDomainEvent {
    private final List<ClientDomainEvent> events;
    private final ClientId clientId;
    private ClientSimpleId id;
    private ClientSecret secret;
    private List<ClientGrantType> grantTypes;
    private List<ClientRedirectUri> redirectUris;
    private List<ClientRedirectUri> postLogoutUris;
    private List<ClientScope> scopes;
    private ClientTokenValidity accessClientTokenValidity;
    private ClientTokenValidity refreshClientTokenValidity;

    public Client(
            ClientSimpleId id,
            ClientId clientId,
            ClientSecret secret,
            List<ClientGrantType> grantTypes,
            List<ClientRedirectUri> redirectUris,
            List<ClientRedirectUri> postLogoutUris,
            List<ClientScope> scopes,
            ClientTokenValidity accessClientTokenValidity,
            ClientTokenValidity refreshClientTokenValidity
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(clientId);
        Objects.requireNonNull(secret);
        Objects.requireNonNull(grantTypes);
        Objects.requireNonNull(redirectUris);
        Objects.requireNonNull(postLogoutUris);
        Objects.requireNonNull(scopes);
        Objects.requireNonNull(accessClientTokenValidity);
        Objects.requireNonNull(refreshClientTokenValidity);

        this.events = new ArrayList<>();
        this.id = id;
        this.clientId = clientId;
        this.secret = secret;
        this.grantTypes = grantTypes;
        this.redirectUris = redirectUris;
        this.postLogoutUris = postLogoutUris;
        this.scopes = scopes;
        this.accessClientTokenValidity = accessClientTokenValidity;
        this.refreshClientTokenValidity = refreshClientTokenValidity;
    }

    public static Client createBy(ClientCreateCommand command) {
        if (command == null) throw new IllegalArgumentException("command cannot be null");

        final ClientSimpleId simpleId = ClientSimpleId.generate();
        final Client client = new Client(
                simpleId,
                command.clientId(),
                command.clientSecret(),
                command.grantTypes(),
                command.redirectUris(),
                command.postLogoutUris(),
                command.scopes(),
                command.accessClientTokenValidity(),
                command.refreshClientTokenValidity()
        );

        client.events.add(ClientCreatedEvent.issue(client.getClientId()));
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
