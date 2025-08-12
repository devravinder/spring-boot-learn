package com.paravar;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AppUserRepositoryImpl implements AppUserRepository {
    // we can get this from DB
    private final Map<String, AppUser> users;

    public AppUserRepositoryImpl() {
        /*
          PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
          String encodedPassword = passwordEncoder.encode("password");
        * */

        // the raw password is "password"
        String encodedPassword = "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm";

        AppUser customUser = new AppUser(1L, "user@example.com", encodedPassword);
        Map<String, AppUser> emailToCustomUser = new HashMap<>();
        emailToCustomUser.put(customUser.getEmail(), customUser);
        this.users = emailToCustomUser;
    }


    @Override
    public AppUser findCustomUserByEmail(String email) {
        return this.users.get(email);
    }
}
