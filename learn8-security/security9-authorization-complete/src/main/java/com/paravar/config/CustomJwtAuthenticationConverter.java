package com.paravar.config;

import com.paravar.user.EmployeeDetails;
import com.paravar.user.EmployeeDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final EmployeeDetailsService employeeDetailsService;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        try {
            String username = jwt.getSubject();

            // Create EmployeeDetails from the user
            UserDetails employeeDetails = employeeDetailsService.loadUserByUsername(username);
            
            // Extract authorities from JWT scope claim
            Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
            
            // Create authentication token with EmployeeDetails as principal
            return new UsernamePasswordAuthenticationToken(
                    employeeDetails, // This will be available as @AuthenticationPrincipal
                    null, 
                    authorities
            );
            
        } catch (Exception e) {
            log.error("Error converting JWT to Authentication: {}", e.getMessage());
            throw new RuntimeException("JWT conversion failed", e);
        }
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        String scope = jwt.getClaimAsString("scope");
        if (scope == null || scope.trim().isEmpty()) {
            return Collections.emptyList();
        }
        
        return Arrays.stream(scope.split(" "))
                .filter(authority -> !authority.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}