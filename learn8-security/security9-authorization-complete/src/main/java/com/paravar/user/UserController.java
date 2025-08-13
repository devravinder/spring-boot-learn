package com.paravar.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /*

    // if we are not using default jwt converter...then the AuthenticationPrincipal is Jwt
    // and the Authentication is Jwt Authentication

    @GetMapping("/me")
    public EmployeeDetails getCurrentUser(@AuthenticationPrincipal Jwt jwt, Authentication jwtAuthentication) {

        System.out.println(jwt.getClaims());
        System.out.println(jwt.getSubject());
        System.out.println("scopes "+jwt.getClaim("scope"));

        System.out.println(jwtAuthentication);
        System.out.println(jwtAuthentication.getPrincipal() == jwt ); // true

        return null;
    }
    */


    /*
     We are suing custom jwt converter...which converts token
      as UsernamePasswordAuthenticationToken & injects EmployeeDetails as AuthenticationPrincipal
    * */

    @GetMapping("/me")
    public EmployeeDetails getCurrentUser(@AuthenticationPrincipal EmployeeDetails employeeDetails) {
        return employeeDetails;
    }
}