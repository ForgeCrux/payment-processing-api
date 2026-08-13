package com.probestack.forgestudio.design.config.security;

import com.probestack.forgestudio.design.security.apikey.ApiKeyAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for generated API key protection.
 */
@Configuration
@EnableConfigurationProperties(ApiKeySecurityProperties.class)
public class SecurityConfig {

    /**
     * Keeps Spring Security stateless while the generated API key filter returns JSON errors.
     */
    @Bean
    public SecurityFilterChain apiKeySecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .build();
    }

    /**
     * Creates the generated API key filter.
     */
    @Bean
    public ApiKeyAuthenticationFilter apiKeyAuthenticationFilter(
            ApiKeySecurityProperties properties,
            ObjectMapper objectMapper
    ) {
        return new ApiKeyAuthenticationFilter(properties, objectMapper);
    }

    /**
     * Registers the generated API key filter before normal application filters.
     */
    @Bean
    public FilterRegistrationBean<ApiKeyAuthenticationFilter> apiKeyAuthenticationFilterRegistration(
            ApiKeyAuthenticationFilter filter
    ) {
        FilterRegistrationBean<ApiKeyAuthenticationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 20);
        return registration;
    }
}
