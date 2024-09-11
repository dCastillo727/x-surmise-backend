package com.xsurmise.authorizationdata.layers.infrastructure.configuration.mapping;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.mapping.AccessTokenJpaMapper;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.accesstoken.mapping.AccessTokenPageMapper;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.blacklisttoken.mapping.BlackListTokenJpaMapper;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.mapping.ClientJpaMapper;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.client.mapping.ClientJpaModifier;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.mapping.RefreshTokenJpaMapper;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.refreshtoken.mapping.RefreshTokenPageMapper;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driven.persistence.jpa.user.mapping.AppUserJpaMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {
    @Bean
    public ClientJpaMapper clientJpaMapper() {
        return new ClientJpaMapper();
    }

    @Bean
    public BlackListTokenJpaMapper blackListTokenJpaMapper() {
        return new BlackListTokenJpaMapper();
    }

    @Bean
    public AccessTokenJpaMapper accessTokenJpaMapper() {
        return new AccessTokenJpaMapper();
    }

    @Bean
    public AccessTokenPageMapper pageMapper(AccessTokenJpaMapper accessTokenJpaMapper) {
        return new AccessTokenPageMapper(accessTokenJpaMapper);
    }

    @Bean
    public ClientJpaModifier clientJpaModifier() {
        return new ClientJpaModifier();
    }

    @Bean
    public RefreshTokenJpaMapper refreshTokenJpaMapper() {
        return new RefreshTokenJpaMapper();
    }

    @Bean
    public RefreshTokenPageMapper refreshTokenPageMapper(RefreshTokenJpaMapper refreshTokenJpaMapper) {
        return new RefreshTokenPageMapper(refreshTokenJpaMapper);
    }

    @Bean
    public AppUserJpaMapper appUserJpaMapper() {
        return new AppUserJpaMapper();
    }
}
