package com.beauty.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class WebConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow all origins (can be restricted to specific domains in production)
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        // Allow common HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        // Allow all headers
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        // Allow credentials (for cookies, authorization headers, etc.)
        configuration.setAllowCredentials(true);
        // Cache CORS preflight response for 1 hour
        configuration.setMaxAge(3600L);
        // Allow specific headers in responses
        configuration.setExposedHeaders(Arrays.asList("Authorization", "X-Total-Count", "X-Page-Count"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
