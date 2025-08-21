package com.paravar;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;


public class AccessControl {

    private Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication);
    }

    private Optional<CustomJwtAuthenticationToken> getJwtAuthentication() {
        // if multiple authentications are available...then check instanceof
        return getAuthentication().map(auth-> (CustomJwtAuthenticationToken)auth);
    }

    private Optional<AppUser> getUserDetails(){
        return getJwtAuthentication().map(CustomJwtAuthenticationToken::getPrincipal);
    }

    public boolean isAuthenticated(){
        return getAuthentication().isPresent();
    }

    public boolean hasPermission(String permission){
        return getUserDetails().map(userDetails-> userDetails.getAuthorities()
                .stream()
                        .anyMatch(authority-> Objects.equals(authority.getAuthority(), permission)))
                .orElse(Boolean.FALSE);

    }

    public boolean canUpdate(Long id, AppUser userDetails){
        return Objects.equals(id, userDetails.getId());
    }

}