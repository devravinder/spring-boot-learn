package com.paravar.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Permission implements GrantedAuthority {
    private final String scope;
    private final String authority;

    @Override
    public String getAuthority() {
        return scope + ":" + authority;
    }

    @Override
    public String toString(){
        return  getAuthority();
    }
}