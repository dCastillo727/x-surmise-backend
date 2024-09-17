package com.xsurmise.authorizationserver.layers.infrastructure.adapter.driven.persistence.grpc.client.mapping;

import com.xsurmise.authorizationserver.ClientCreateRequest;
import com.xsurmise.authorizationserver.common.utils.mapping.MapperDrivenDtoRequest;
import com.xsurmise.authorizationserver.layers.domain.model.client.Client;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientGrantType;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientRedirectUri;
import com.xsurmise.authorizationserver.layers.domain.model.client.ClientScope;

public class ClientGrpcRequestMapper implements MapperDrivenDtoRequest<ClientCreateRequest, Client> {
    @Override
    public ClientCreateRequest domainToDto(Client domain) {
        ClientCreateRequest.Builder builder = ClientCreateRequest.newBuilder();
        return builder
                .setClientId(domain.getClientId().value())
                .setClientSecret(domain.getSecret().value())
                .addAllGrantTypes(domain.getGrantTypes().stream().map(ClientGrantType::getValue).toList())
                .addAllRedirectUris(domain.getRedirectUris().stream().map(ClientRedirectUri::value).toList())
                .addAllPostLogoutUris(domain.getPostLogoutUris().stream().map(ClientRedirectUri::value).toList())
                .addAllScopes(domain.getScopes().stream().map(ClientScope::getValue).toList())
                .setAccessTokenValidity(domain.getAccessClientTokenValidity().value())
                .setRefreshTokenValidity(domain.getRefreshClientTokenValidity().value())
                .build();
    }
}
