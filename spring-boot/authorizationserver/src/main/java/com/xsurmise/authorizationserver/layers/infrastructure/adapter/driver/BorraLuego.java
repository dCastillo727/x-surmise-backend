package com.xsurmise.authorizationserver.layers.infrastructure.adapter.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BorraLuego {
    private final RegisteredClientRepository registeredClientRepository;

    @GetMapping("/test")
    public boolean wea() {
        registeredClientRepository.save(
                RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("mi-test-weon")
                        .clientSecret("123456")
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                        .redirectUri("http://localhost:8080/v1/algunsitio")
                        .postLogoutRedirectUri("http://localhost:8080/v1/logout")
                        .scope("read")
                        .scope("write")
                        .tokenSettings(
                                TokenSettings.builder()
                                        .accessTokenTimeToLive(Duration.of(5000, ChronoUnit.SECONDS))
                                        .refreshTokenTimeToLive(Duration.of(727000, ChronoUnit.SECONDS))
                                        .build()
                        )
                        .build()
        );

        return true;
    }
}
