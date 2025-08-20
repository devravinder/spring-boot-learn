package com.paravar;

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
    // need message:read scope to access
    public String message() {
        return "Some message";
    }
    @PostMapping("/message")
    // need  message:write scope to access
    public String postMessage(@RequestBody String message) {
        return "Hello, the message is: "+message;
    }
    @PutMapping("/message")
    // need  message:update scope to access
    public String putMessage(@RequestBody String message) {
        return "Hello, the message is: "+message;
    }




}