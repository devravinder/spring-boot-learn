package com.paravar;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Util {

    // Define role-to-permission mappings ( or get from DB/cache)
    public static final Map<String, Set<CustomGrantedAuthority>> ROLE_TO_PERMISSIONS = new HashMap<>();

    static {
        ROLE_TO_PERMISSIONS.put("MESSAGE_R", Set.of(
                new CustomGrantedAuthority("message", "read")
        ));
        ROLE_TO_PERMISSIONS.put("MESSAGE_RW", Set.of(
                new CustomGrantedAuthority("message", "read"),
                new CustomGrantedAuthority("message", "write")
        ));
        ROLE_TO_PERMISSIONS.put("MESSAGE_RWU", Set.of(
                new CustomGrantedAuthority("message", "read"),
                new CustomGrantedAuthority("message", "write"),
                new CustomGrantedAuthority("message", "update")
        ));

        //===

        ROLE_TO_PERMISSIONS.put("USER_R", Set.of(
                new CustomGrantedAuthority("users", "read")
        ));
        ROLE_TO_PERMISSIONS.put("USER_RW", Set.of(
                new CustomGrantedAuthority("users", "read"),
                new CustomGrantedAuthority("users", "write")
        ));
        ROLE_TO_PERMISSIONS.put("USER_RWU", Set.of(
                new CustomGrantedAuthority("users", "read"),
                new CustomGrantedAuthority("users", "write"),
                new CustomGrantedAuthority("users", "update")
        ));

        //

        ROLE_TO_PERMISSIONS.put("ADMIN", ROLE_TO_PERMISSIONS.values()
                .stream().flatMap(Collection::stream)
                .collect(Collectors.toSet()));


    }

    public static Set<CustomGrantedAuthority> getPermissions(List<String> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }

        return roles.stream()
                .map(ROLE_TO_PERMISSIONS::get)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
