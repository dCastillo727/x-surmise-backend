package com.xsurmise.authorizationserver.layers.domain.command.client;

import com.xsurmise.authorizationserver.common.utils.command.Command;
import com.xsurmise.authorizationserver.common.utils.command.CommandId;
import com.xsurmise.authorizationserver.layers.domain.model.client.*;

import java.time.Instant;
import java.util.List;

public record ClientCreateCommand(
        CommandId id,
        Instant registeredAt,
        ClientId clientId,
        ClientSecret clientSecret,
        List<ClientGrantType> grantTypes,
        List<ClientRedirectUri> redirectUris,
        List<ClientRedirectUri> postLogoutUris,
        List<ClientScope> scopes,
        ClientTokenValidity accessClientTokenValidity,
        ClientTokenValidity refreshClientTokenValidity
) implements Command {
    public static ClientCreateCommand issue(
            ClientId clientId,
            ClientSecret clientSecret,
            List<ClientGrantType> grantTypes,
            List<ClientRedirectUri> redirectUris,
            List<ClientRedirectUri> postLogoutUris,
            List<ClientScope> scopes,
            ClientTokenValidity accessClientTokenValidity,
            ClientTokenValidity refreshClientTokenValidity
    ) {
        return new ClientCreateCommand(
                CommandId.generate(),
                Command.now(),
                clientId,
                clientSecret,
                grantTypes,
                redirectUris,
                postLogoutUris,
                scopes,
                accessClientTokenValidity,
                refreshClientTokenValidity
        );
    }
}
