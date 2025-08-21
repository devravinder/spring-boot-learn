package com.paravar;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public String hello(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println(authentication);
        System.out.println(userDetails);
        return "Hello, JWT Secured World!";
    }


    //==============
    @GetMapping("/message")
    @PreAuthorize("@access.hasPermission('message:read')")
    public String message() {
        return "Some message";
    }

    @PostMapping("/message")
    @PreAuthorize("@access.hasPermission('message:write')")
    public String postMessage(@RequestBody String message) {
        return "Hello, the message is: "+message;
    }

    @PutMapping("/message")
    @PreAuthorize("@access.hasPermission('message:update')")
    public String putMessage(@RequestBody String message) {
        return "Hello, the message is: "+message;
    }




    //=======
    @GetMapping("/users")
    @PreAuthorize("@access.hasPermission('users:read')")
    public String users() {
        return "Some message";
    }


    @PostMapping("/users")
    @PreAuthorize("@access.hasPermission('users:write')")
    public String postUser() {
        return "Hello, the message is: ";
    }

    @PutMapping("/users")
    @PreAuthorize("@access.hasPermission('users:update')")
    public String putUser() {
        return "Hello, the message is: ";
    }

    //======
    @PutMapping("/users/{id}/password-update")
    @PreAuthorize("@access.canUpdate(#id, #userDetails)")
    public String passwordUpdate(@PathVariable("id") Long id, @AuthenticationPrincipal AppUser userDetails) {
        return "updated";
    }


}