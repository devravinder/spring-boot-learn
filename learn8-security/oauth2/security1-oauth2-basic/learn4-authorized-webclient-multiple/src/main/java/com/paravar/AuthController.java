package com.paravar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/github")
    public String loginWithPrimary() {
        return "redirect:/oauth2/authorization/github";
    }

    @GetMapping("/github-secondary")
    public String loginWithSecondary() {
        return "redirect:/oauth2/authorization/github-secondary";
    }
    
    @GetMapping("/status")
    public String authStatus() {
        return "auth-status"; // You can create an HTML page to show auth status
    }
}