package com.paravar;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();


    @GetMapping("/")
    String home(){
        return "index";
    }

    @GetMapping("/secure")
    String secure(){
        return "secure";
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/logout")
    String logout(){
        return "logout";
    }
    @GetMapping("/logout-proceed")
    String logoutProceed(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        // perform logout
        this.logoutHandler.logout(request, response, authentication);
        return "logoutSuccess";
    }
    @GetMapping("/logoutSuccess")
    String logoutSuccess(){
        return "logoutSuccess";
    }
}
