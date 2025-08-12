package com.paravar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    // public AppUser user(@CurrentUser AppUser currentUser)
    public AppUser user(@CurrentUser CustomUserDetails currentUser) {

        /*
         CustomUserDetails extends AppUser
        */

        return currentUser;
    }


}
