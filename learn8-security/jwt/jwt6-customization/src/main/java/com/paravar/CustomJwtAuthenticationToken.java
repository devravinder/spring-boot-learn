package com.paravar;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;

public class CustomJwtAuthenticationToken extends AbstractAuthenticationToken {
    @Getter
    private final Jwt jwt;
    private final AppUser principal;

    public CustomJwtAuthenticationToken(Jwt jwt, AppUser principal, Set<CustomGrantedAuthority> authorities) {
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
    public AppUser getPrincipal() {
        return principal;
    }

}