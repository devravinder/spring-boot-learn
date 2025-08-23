package com.paravar.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paravar.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
// we can extend Employee if needed
public class EmployeeDetails implements UserDetails {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Role role;
    private Long managerId;
    private final List<GrantedAuthority> authorities;


    public EmployeeDetails(Long id, String username, String password, Role role, Long managerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.managerId = managerId;
        this.authorities = Collections.unmodifiableList(
                AuthorityUtils.createAuthorityList("ROLE_" + role.name().toUpperCase())
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
