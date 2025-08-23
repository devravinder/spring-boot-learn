package com.paravar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth").permitAll()
                    .requestMatchers("/api/primary/**").authenticated()  // ✅ Require auth for primary endpoints
                    .requestMatchers("/api/secondary/**").authenticated() // ✅ Require auth for secondary endpoints

                    .anyRequest().permitAll()
            )
                .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/api", true)
                .authorizationEndpoint(auth -> auth
                        .baseUri("/oauth2/authorization")
                )
        );
        return http.build();
    }
}