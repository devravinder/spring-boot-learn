package com.paravar.auth.model;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;

public class JwtAuthToken extends AbstractAuthenticationToken {
    @Getter
    private final Jwt jwt;
    private final AuthPrincipal principal;

    public JwtAuthToken(Jwt jwt, AuthPrincipal principal, Set<Permission> authorities) {
        super(authorities);
        this.jwt = jwt;
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public AuthPrincipal getPrincipal() {
        return principal;
    }

}