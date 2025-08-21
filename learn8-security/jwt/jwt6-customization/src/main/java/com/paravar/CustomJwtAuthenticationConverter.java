package com.paravar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Set;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String ROLES_CLAIM = "roles";
    private static final String ID_CLAIM = "id";


    @Override

    public AbstractAuthenticationToken convert(Jwt jwt) {

            Set<CustomGrantedAuthority> authorities = extractAuthorities(jwt);
            AppUser userDetails = new AppUser(
                    jwt.getClaim(ID_CLAIM),
                    jwt.getSubject(),
                    jwt.getClaimAsStringList(ROLES_CLAIM)
            );

            return new CustomJwtAuthenticationToken(jwt, userDetails, authorities);

    }

    private Set<CustomGrantedAuthority> extractAuthorities(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList(ROLES_CLAIM);
        return Util.getPermissions(roles);
    }
}