package com.xsurmise.authorizationdata.layers.infrastructure.configuration.mapping;

import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driver.grpc.client.mapping.ClientRequestGrpcMapper;
import com.xsurmise.authorizationdata.layers.infrastructure.adapter.driver.grpc.client.mapping.ClientResponseGrpcMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcMappersConfig {
    @Bean
    public ClientResponseGrpcMapper clientResponse() {
        return new ClientResponseGrpcMapper();
    }

    @Bean
    public ClientRequestGrpcMapper clientRequest() {
        return new ClientRequestGrpcMapper();
    }
}
