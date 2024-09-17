package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driver.grpc.client.mapping;

import com.xsurmise.authorizationdata.ClientResponse;
import com.xsurmise.authorizationdata.common.utils.mapping.MapperDtoResponse;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.GrantType;
import com.xsurmise.authorizationdata.layers.domain.model.client.RedirectUri;
import com.xsurmise.authorizationdata.layers.domain.model.client.Scope;

public class ClientResponseGrpcMapper implements MapperDtoResponse<ClientResponse, Client> {

    @Override
    public ClientResponse transform(Client domainModel) {
        return ClientResponse.newBuilder()
                .setId(domainModel.getId().id())
                .setClientId(domainModel.getClientId().value())
                .setClientSecret(domainModel.getClientSecret().value())
                .setAccessTokenValidity(domainModel.getAccessTokenValidity().duration())
                .setRefreshTokenValidity(domainModel.getRefreshTokenValidity().duration())
                .addAllGrantTypes(
                        domainModel.getGrantTypes().stream().map(GrantType::getValue).toList()
                )
                .addAllRedirectUris(
                        domainModel.getRedirectUris().stream().map(RedirectUri::uri).toList()
                )
                .addAllPostLogoutUris(
                        domainModel.getPostLogoutUris().stream().map(RedirectUri::uri).toList()
                )
                .addAllScopes(
                        domainModel.getScopes().stream().map(Scope::getValue).toList()
                ).build();
    }
}
