package com.paravar.auth.model;

import com.paravar.domain.Role;

import java.util.Set;

public record AuthPrincipal(Long id, String username, String name, Role role, Set<Permission> permissions) {
}
