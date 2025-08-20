package com.paravar;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails extends AppUser implements UserDetails {

    private  final List<GrantedAuthority> authorities = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));

    public CustomUserDetails(AppUser appUser) {
        super(appUser.getId(), appUser.getEmail(), appUser.getPassword());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }
}
