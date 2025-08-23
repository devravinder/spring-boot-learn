package com.paravar.util;

import com.paravar.auth.model.Permission;
import com.paravar.domain.Role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Constants {

    public static final String ID_CLAIM = "id";
    public static final String ROLE_CLAIM = "role";
    public static final String NAME_CLAIM = "name";

    // Define role-to-permission mappings ( or get from DB/cache)
    public static final Map<Role, Set<Permission>> ROLE_TO_PERMISSIONS = new HashMap<>();

    static {


        ROLE_TO_PERMISSIONS.put(Role.DEVELOPER, Set.of(
                new Permission("employees", "read"),
                new Permission("projects", "read")

        ));
        ROLE_TO_PERMISSIONS.put(Role.TEAM_LEAD, Set.of(
                new Permission("employees", "read"),
                new Permission("employees", "write"),

                new Permission("projects", "read"),
                new Permission("projects", "write")
        ));
        ROLE_TO_PERMISSIONS.put(Role.MANAGER, Set.of(
                new Permission("employees", "read"),
                new Permission("employees", "write"),
                new Permission("employees", "update"),

                new Permission("projects", "read"),
                new Permission("projects", "write"),
                new Permission("projects", "update")
        ));

        //

        ROLE_TO_PERMISSIONS.put(Role.CTO, ROLE_TO_PERMISSIONS.values()
                .stream().flatMap(Collection::stream)
                .collect(Collectors.toSet()));
    }

}
