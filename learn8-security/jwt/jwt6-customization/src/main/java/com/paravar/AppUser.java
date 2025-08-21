package com.paravar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@ToString
public class AppUser /*implements UserDetails*/ {
   // implementing UserDetails is not necessary, as we are not using user details service


    @Getter
    private Long id;
    @Getter
    private String username;
    @JsonIgnore
    private String password;
    @Getter
    private List<String> roles;
    @Getter
    private final Set<CustomGrantedAuthority> authorities;

    AppUser(Long id, String username, List<String> roles){
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.authorities = Util.getPermissions(roles);
    }


}
