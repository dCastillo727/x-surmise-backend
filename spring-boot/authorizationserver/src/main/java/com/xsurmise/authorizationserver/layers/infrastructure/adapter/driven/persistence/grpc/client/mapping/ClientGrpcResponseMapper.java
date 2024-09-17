package com.xsurmise.authorizationserver.layers.infrastructure.adapter.driven.persistence.grpc.client.mapping;

import com.xsurmise.authorizationserver.ClientResponse;
import com.xsurmise.authorizationserver.common.utils.mapping.MapperDrivenDtoResponse;
import com.xsurmise.authorizationserver.layers.domain.model.client.*;

public class ClientGrpcResponseMapper implements MapperDrivenDtoResponse<ClientResponse, Client> {
    @Override
    public Client responseToDomain(ClientResponse dto) {
        return new Client(
                ClientSimpleId.from(dto.getId()),
                ClientId.from(dto.getClientId()),
                ClientSecret.from(dto.getClientSecret()),
                dto.getGrantTypesList().stream().map(ClientGrantType::fromValue).toList(),
                dto.getRedirectUrisList().stream().map(ClientRedirectUri::from).toList(),
                dto.getRedirectUrisList().stream().map(ClientRedirectUri::from).toList(),
                dto.getScopesList().stream().map(ClientScope::fromValue).toList(),
                ClientTokenValidity.of(dto.getAccessTokenValidity()),
                ClientTokenValidity.of(dto.getRefreshTokenValidity())
        );
    }
}
