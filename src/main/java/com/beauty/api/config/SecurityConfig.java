package com.beauty.api.config;

import com.beauty.api.security.JwtAuthenticationFilter;
import com.beauty.api.security.JwtAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Allow preflight requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Public auth endpoints
                        .requestMatchers("/auth/**").permitAll()

                        // Public read-only endpoints (for customers browsing)
                        .requestMatchers(HttpMethod.GET, "/company/**", "/branch/**", "/service/**", "/serviceCategory/**").permitAll()

                        // Swagger
                        .requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**").permitAll()

                        // ADMIN only
                        .requestMatchers(HttpMethod.POST, "/company", "/company/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/company/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/company/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/userGroup/**").hasRole("ADMIN")

                        // ADMIN or OWNER
                        .requestMatchers("/employee/**").hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers(HttpMethod.POST, "/service", "/service/**").hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers(HttpMethod.PUT, "/service/**").hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers(HttpMethod.DELETE, "/service/**").hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers(HttpMethod.POST, "/serviceCategory", "/serviceCategory/**").hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers(HttpMethod.PUT, "/serviceCategory/**").hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers(HttpMethod.DELETE, "/serviceCategory/**").hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers("/client/**").hasAnyRole("ADMIN", "OWNER")

                        // ADMIN, OWNER, or EMP
                        .requestMatchers("/appointment/**").hasAnyRole("ADMIN", "OWNER", "EMP")
                        .requestMatchers("/appointmentStatus/**").hasAnyRole("ADMIN", "OWNER", "EMP")

                        // Any authenticated user
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
