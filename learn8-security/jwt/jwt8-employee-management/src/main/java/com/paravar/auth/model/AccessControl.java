package com.paravar.auth.model;

import com.paravar.domain.Employee;
import com.paravar.domain.Role;
import com.paravar.employee.model.EmployeeListItem;
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

    private Optional<JwtAuthToken> getJwtAuthentication() {
        // if multiple authentications are available...then check instanceof
        return getAuthentication().map(auth-> (JwtAuthToken)auth);
    }

    private Optional<AuthPrincipal> getAuthPrincipal(){
        return getJwtAuthentication().map(JwtAuthToken::getPrincipal);
    }

    public boolean isAuthenticated(){
        return getAuthentication().map(Authentication::isAuthenticated).orElse(Boolean.FALSE);
    }

    public boolean hasPermission(String permission){
        return getAuthPrincipal().map(userDetails-> userDetails.permissions()
                .stream()
                        .anyMatch(authority-> Objects.equals(authority.getAuthority(), permission)))
                .orElse(Boolean.FALSE);

    }

    public boolean canUpdate(Long id, Employee userDetails){
        return Objects.equals(id, userDetails.getId());
    }
    public boolean canFetchEmployeeDetails(EmployeeListItem item){
        return getAuthPrincipal()
                .map(authPrincipal ->Objects.equals(authPrincipal.id(), item.id()) || Objects.equals(authPrincipal.id(), item.managerId()))
                .orElse(Boolean.FALSE);
    }
    public boolean canFetchReporters(AuthPrincipal principal, Long managerId){
        return principal.role().equals(Role.CTO) || Objects.equals(principal.id(), managerId);
    }
    public boolean isCTO(){
        return getAuthPrincipal().map(principal->principal.role().equals(Role.CTO))
                .orElse(Boolean.FALSE);
    }
    public boolean isCTO(AuthPrincipal principal){
        return principal.role().equals(Role.CTO);
    }

}