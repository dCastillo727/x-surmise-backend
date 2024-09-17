package com.xsurmise.authorizationdata.layers.domain.command.client;

import com.xsurmise.authorizationdata.common.utils.command.Command;
import com.xsurmise.authorizationdata.common.utils.command.CommandId;
import com.xsurmise.authorizationdata.layers.domain.model.client.*;

import java.time.Instant;
import java.util.List;

public record CreateClientCommand(
        CommandId id,
        Instant registeredAt,
        ClientId clientId,
        ClientSecret clientSecret,
        List<GrantType> grantTypes,
        List<RedirectUri> redirectUris,
        List<RedirectUri> postLogoutUris,
        List<Scope> scopes,
        TokenValidity accessTokenValidity,
        TokenValidity refreshTokenValidity
) implements Command {
    public static CreateClientCommand issue(
            ClientId clientId,
            ClientSecret clientSecret,
            List<GrantType> grantTypes,
            List<RedirectUri> redirectUris,
            List<RedirectUri> postLogoutUris,
            List<Scope> scopes,
            TokenValidity accessTokenValidity,
            TokenValidity refreshTokenValidity
    ) {
        return new CreateClientCommand(
                CommandId.generate(),
                Command.now(),
                clientId,
                clientSecret,
                grantTypes,
                redirectUris,
                postLogoutUris,
                scopes,
                accessTokenValidity,
                refreshTokenValidity
        );
    }
}
