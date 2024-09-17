package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driver.grpc.client.mapping;

import com.xsurmise.authorizationdata.ClientCreateRequest;
import com.xsurmise.authorizationdata.common.utils.command.Command;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperDtoRequest;
import com.xsurmise.authorizationdata.layers.domain.command.client.CreateClientCommand;
import com.xsurmise.authorizationdata.layers.domain.model.client.*;

public class ClientRequestGrpcMapper implements MapperDtoRequest<ClientCreateRequest, Client> {
    @Override
    public Client toDomainModel(ClientCreateRequest dto) {
        return null;
    }

    @Override
    public <T extends Command> T toCommandFromDto(ClientCreateRequest dto, Class<T> commandClass) {
        if (commandClass == CreateClientCommand.class) {
            CreateClientCommand command = toCommandFromDto(dto);
            return commandClass.cast(command);
        }
        return null;
    }

    private CreateClientCommand toCommandFromDto(ClientCreateRequest dto) {
        return CreateClientCommand.issue(
                ClientId.from(dto.getClientId()),
                ClientSecret.from(dto.getClientSecret()),
                dto.getGrantTypesList().stream().map(GrantType::fromValue).toList(),
                dto.getRedirectUrisList().stream().map(RedirectUri::new).toList(),
                dto.getPostLogoutUrisList().stream().map(RedirectUri::new).toList(),
                dto.getScopesList().stream().map(Scope::fromValue).toList(),
                TokenValidity.of(dto.getAccessTokenValidity()),
                TokenValidity.of(dto.getRefreshTokenValidity())
        );
    }
}
