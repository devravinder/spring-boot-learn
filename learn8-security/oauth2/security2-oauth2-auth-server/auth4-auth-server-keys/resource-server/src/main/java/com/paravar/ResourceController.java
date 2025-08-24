package com.paravar;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/resource")
    public String getResource(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("jwt "+jwt.getTokenValue());
        return "Protected resource accessed by: " + jwt.getClaim("sub");
    }
}