package com.paravar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestCmd requestCmd) {

        // do authentication using credentials

        // then issue token
        return jwtService.generateToken(1L, requestCmd.username(), List.of("USER_R", "MESSAGE_R"));
    }

    @PostMapping("/login/manager")
    public String loginManager(@RequestBody LoginRequestCmd requestCmd) {
        // do authentication using credentials

        // then issue token
        return jwtService.generateToken(2L,requestCmd.username(), List.of("USER_RW", "MESSAGE_RW"));
    }

    @PostMapping("/login/admin")
    public String loginAdmin(@RequestBody LoginRequestCmd requestCmd) {
        // do authentication using credentials

        // then issue token
        return jwtService.generateToken(3L, requestCmd.username(), List.of("ADMIN"));
    }
}