package com.fastturtle.apigateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("fortune-producer", r -> r
                        .path("/fortune-service/**") // incoming path to gateway
                        .filters(f -> f.stripPrefix(1)) // removes '/fortune-service' before forwarding
                        .uri("http://localhost:8100")) // target microservice
                .build();
    }
}
