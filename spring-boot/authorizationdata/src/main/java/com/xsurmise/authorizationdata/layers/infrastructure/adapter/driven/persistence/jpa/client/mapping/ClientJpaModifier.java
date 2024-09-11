package com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.mapping;

import com.xsurmise.authorizationdata.common.utils.mapping.ModifierMapper;
import com.xsurmise.authorizationdata.layers.domain.model.client.Client;
import com.xsurmise.authorizationdata.layers.domain.model.client.GrantType;
import com.xsurmise.authorizationdata.layers.domain.model.client.RedirectUri;
import com.xsurmise.authorizationdata.layers.domain.model.client.Scope;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.entity.ClientJpaEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientJpaModifier implements ModifierMapper<Client, ClientJpaEntity> {
    private Client domainClient;

    @Override
    public ClientJpaModifier applyChangesFrom(Client origin) {
        return new ClientJpaModifier(origin);
    }

    @Override
    public ClientJpaEntity to(final ClientJpaEntity end) {
        end.setId(domainClient.getId().id());
        end.setClientId(domainClient.getClientId().value());
        end.setClientSecret(domainClient.getClientSecret().value());
        end.setAccessTokenValidity(domainClient.getAccessTokenValidity().duration());
        end.setRefreshTokenValidity(domainClient.getRefreshTokenValidity().duration());
        end.setGrantTypes(domainClient.getGrantTypes().stream().map(GrantType::getValue).toArray(String[]::new));
        end.setRedirectUris(domainClient.getRedirectUris().stream().map(RedirectUri::uri).toArray(String[]::new));
        end.setScopes(domainClient.getScopes().stream().map(Scope::getValue).toArray(String[]::new));

        return end;
    }
}
