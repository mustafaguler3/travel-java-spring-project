package com.example.tour_travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] publicUrl = {
            "/",
            "/home",
            "/css/**",
            "/js/**",
            "/images/**",
            "/favicon.ico",
            "/v2/api-docs",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeRequests()
                .requestMatchers(publicUrl).permitAll();


        return http.build();
    }
}



















