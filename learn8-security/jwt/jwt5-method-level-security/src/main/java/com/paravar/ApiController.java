package com.paravar;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public String hello(Authentication authentication, @AuthenticationPrincipal Jwt authenticationPrincipal) {

        System.out.println(authentication);
        System.out.println(authenticationPrincipal);
        return "Hello, JWT Secured World!";
    }


    //==============
    @GetMapping("/message")
    @PreAuthorize("hasAuthority('SCOPE_message:read')")
    public String message() {
        return "Some message";
    }

    @PostMapping("/message")
    @PreAuthorize("hasAuthority('SCOPE_message:write')")
    public String postMessage(@RequestBody String message) {
        return "Hello, the message is: "+message;
    }

    @PutMapping("/message")
    @PreAuthorize("hasAuthority('SCOPE_message:update')")
    public String putMessage(@RequestBody String message) {
        return "Hello, the message is: "+message;
    }




    //=======
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER')")
    public String users() {
        return "Some message";
    }


    @PostMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public String postUser() {
        return "Hello, the message is: ";
    }

    @PutMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public String putUser() {
        return "Hello, the message is: ";
    }


}