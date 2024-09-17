package com.xsurmise.authorizationserver.layers.infrastructure.providers.detailservices.client;

import com.xsurmise.authorizationserver.common.utils.exceptions.client.ClientNotFoundException;
import com.xsurmise.authorizationserver.layers.application.core.client.usecase.ClientCreateUseCase;
import com.xsurmise.authorizationserver.layers.application.core.client.usecase.ClientGetUseCases;
import com.xsurmise.authorizationserver.layers.domain.command.client.ClientCreateCommand;
import com.xsurmise.authorizationserver.layers.domain.model.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class AuthClientRegistrationProvider implements RegisteredClientRepository {
    private final ClientGetUseCases clientGetUseCases;
    private final ClientCreateUseCase clientCreateUseCase;

    @Override
    public void save(RegisteredClient registeredClient) {
        List<ClientScope> scopes = new ArrayList<>();

        for (String scope : registeredClient.getScopes()) {
            if (!Objects.equals(scope, OidcScopes.OPENID) && !Objects.equals(scope, OidcScopes.PROFILE)) {
                scopes.add(ClientScope.fromValue(scope));
            }
        }

        ClientCreateCommand command = ClientCreateCommand.issue(
                ClientId.from(registeredClient.getClientId()),
                ClientSecret.from(registeredClient.getClientSecret()),
                registeredClient.getAuthorizationGrantTypes().stream().map(this::getClientGrantType).toList(),
                registeredClient.getRedirectUris().stream().map(ClientRedirectUri::new).toList(),
                registeredClient.getPostLogoutRedirectUris().stream().map(ClientRedirectUri::new).toList(),
                scopes,
                ClientTokenValidity.of((int) registeredClient.getTokenSettings().getAccessTokenTimeToLive().getSeconds()),
                ClientTokenValidity.of((int) registeredClient.getTokenSettings().getRefreshTokenTimeToLive().getSeconds())
        );

        clientCreateUseCase.createClient(command);
    }

    @Override
    public RegisteredClient findById(String id) {
        ClientSimpleId clientId = new ClientSimpleId(id);

        Client domainClient = clientGetUseCases.getById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        return mapDomainClientToRegisteredClient(domainClient);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientId domainClientId = new ClientId(clientId);

        Client domainClient = clientGetUseCases.getByClientId(domainClientId)
                .orElseThrow(() -> new ClientNotFoundException(domainClientId));

        return mapDomainClientToRegisteredClient(domainClient);
    }

    public AuthorizationGrantType getAuthorizationGrantType(ClientGrantType grantType) {
        return switch (grantType) {
            case CLIENT_CREDENTIALS -> AuthorizationGrantType.CLIENT_CREDENTIALS;
            case AUTHORIZATION_CODE -> AuthorizationGrantType.AUTHORIZATION_CODE;
            case REFRESH_TOKEN -> AuthorizationGrantType.REFRESH_TOKEN;
            case DEVICE_CODE -> AuthorizationGrantType.DEVICE_CODE;
            case JWT_BEARER -> AuthorizationGrantType.JWT_BEARER;
            case TOKEN_EXCHANGE -> AuthorizationGrantType.TOKEN_EXCHANGE;
        };
    }

    public ClientGrantType getClientGrantType(AuthorizationGrantType grantType) {
        if (grantType == AuthorizationGrantType.CLIENT_CREDENTIALS) {
            return ClientGrantType.CLIENT_CREDENTIALS;
        } else if (grantType == AuthorizationGrantType.AUTHORIZATION_CODE) {
            return ClientGrantType.AUTHORIZATION_CODE;
        } else if (grantType == AuthorizationGrantType.REFRESH_TOKEN) {
            return ClientGrantType.REFRESH_TOKEN;
        } else if (grantType == AuthorizationGrantType.DEVICE_CODE) {
            return ClientGrantType.DEVICE_CODE;
        } else if (grantType == AuthorizationGrantType.JWT_BEARER) {
            return ClientGrantType.JWT_BEARER;
        } else if (grantType == AuthorizationGrantType.TOKEN_EXCHANGE) {
            return ClientGrantType.TOKEN_EXCHANGE;
        }
        return ClientGrantType.AUTHORIZATION_CODE;
    }

    public RegisteredClient mapDomainClientToRegisteredClient(Client domainClient) {
        RegisteredClient.Builder registeredClientBuilder = RegisteredClient.withId(domainClient.getId().value())
                .clientId(domainClient.getClientId().value())
                .clientSecret(domainClient.getSecret().value())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(
                                Duration.of(domainClient.getAccessClientTokenValidity().value(), ChronoUnit.SECONDS)
                        )
                        .refreshTokenTimeToLive(
                                Duration.of(domainClient.getRefreshClientTokenValidity().value(), ChronoUnit.SECONDS)
                        )
                        .build());

        for (ClientGrantType grantType : domainClient.getGrantTypes()) {
            registeredClientBuilder.authorizationGrantType(getAuthorizationGrantType(grantType));
        }

        for (ClientScope scope : domainClient.getScopes()) {
            registeredClientBuilder.scope(scope.getValue());
        }

        for (ClientRedirectUri redirectUris : domainClient.getRedirectUris()) {
            registeredClientBuilder.redirectUri(redirectUris.value());
        }

        for (ClientRedirectUri postLogoutUri : domainClient.getPostLogoutUris()) {
            registeredClientBuilder.redirectUri(postLogoutUri.value());
        }

        return registeredClientBuilder.build();
    }
}
