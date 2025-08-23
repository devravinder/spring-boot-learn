package com.paravar.auth.model;

import com.paravar.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Set;

import static com.paravar.util.Constants.*;

public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override

    public AbstractAuthenticationToken convert(Jwt jwt) {

        Set<Permission> authorities = extractAuthorities(jwt);
        AuthPrincipal authPrincipal = new AuthPrincipal(
                jwt.getClaim(ID_CLAIM),
                jwt.getSubject(),
                jwt.getClaim(NAME_CLAIM),
                Role.valueOf(jwt.getClaim(ROLE_CLAIM)),
                authorities
        );

        return new JwtAuthToken(jwt, authPrincipal, authorities);

    }

    private Set<Permission> extractAuthorities(Jwt jwt) {
        Role role = Role.valueOf(jwt.getClaim(ROLE_CLAIM));
        return ROLE_TO_PERMISSIONS.get(role);
    }
}