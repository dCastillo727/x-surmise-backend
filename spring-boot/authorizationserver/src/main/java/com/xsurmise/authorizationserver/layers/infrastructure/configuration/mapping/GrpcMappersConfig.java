package com.xsurmise.authorizationserver.layers.infrastructure.configuration.mapping;

import com.xsurmise.authorizationserver.layers.infrastructure.adapter.driven.persistence.grpc.client.mapping.ClientGrpcRequestMapper;
import com.xsurmise.authorizationserver.layers.infrastructure.adapter.driven.persistence.grpc.client.mapping.ClientGrpcResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcMappersConfig {
    @Bean
    public ClientGrpcResponseMapper clientResponseMapper() {
        return new ClientGrpcResponseMapper();
    }

    @Bean
    public ClientGrpcRequestMapper clientRequestMapper() {
        return new ClientGrpcRequestMapper();
    }
}
