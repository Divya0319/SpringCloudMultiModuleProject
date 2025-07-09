package com.fastturtle.apigateway.configs;

import com.fastturtle.apigateway.filters.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, JwtAuthenticationFilter jwtFilter) {
        return builder.routes()
                .route("fortune-consumer", r -> r
                        .path("/fortune-service/**") // incoming path to gateway
                        .filters(f -> f.stripPrefix(1).filter(jwtFilter.apply(new JwtAuthenticationFilter.Config()))) // removes '/fortune-service' before forwarding
                        .uri("http://localhost:8200")) // target microservice
                .build();
    }
}
