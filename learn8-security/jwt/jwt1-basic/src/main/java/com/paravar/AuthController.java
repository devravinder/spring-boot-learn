package com.paravar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestCmd requestCmd) {

        // do authentication using credentials

        // then issue token
        return jwtService.generateToken(requestCmd.username());
    }
}