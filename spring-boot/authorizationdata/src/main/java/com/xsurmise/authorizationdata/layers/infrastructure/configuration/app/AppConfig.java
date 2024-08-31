package com.xsurmise.authorizationdata.layers.infrastructure.configuration.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.xsurmise.authorizationdata",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.CUSTOM,
                        classes = {
                                PortAndAdapterFilter.class
                        }
                )
        }
)
public class AppConfig {
}
