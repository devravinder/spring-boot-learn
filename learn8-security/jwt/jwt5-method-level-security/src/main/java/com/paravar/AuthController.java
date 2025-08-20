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
        return jwtService.generateToken(requestCmd.username(), List.of("USER"), List.of("message:read","message:write"));
    }

    @PostMapping("/login/manager")
    public String loginManager(@RequestBody LoginRequestCmd requestCmd) {
        // do authentication using credentials

        // then issue token
        return jwtService.generateToken(requestCmd.username(), List.of("MANAGER"), List.of("message:read","message:write","message:update"));
    }

    @PostMapping("/login/admin")
    public String loginAdmin(@RequestBody LoginRequestCmd requestCmd) {
        // do authentication using credentials

        // then issue token
        return jwtService.generateToken(requestCmd.username(), List.of("ADMIN"), List.of("message:read","message:write","message:update"));
    }


}