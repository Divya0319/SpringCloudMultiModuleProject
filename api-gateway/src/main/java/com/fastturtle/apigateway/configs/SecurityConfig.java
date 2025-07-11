package com.fastturtle.apigateway.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain getSecurityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(cors -> cors.disable());
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeExchange(authorize -> authorize.anyExchange().permitAll());

        return httpSecurity.build();

    }
}