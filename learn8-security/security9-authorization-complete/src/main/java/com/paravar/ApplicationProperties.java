package com.paravar;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Validated
@ConfigurationProperties(prefix = "employee-app")
public record ApplicationProperties(
        @NotNull String someKey,
        @NotNull JwtProperties jwt
) {
    public record JwtProperties(
            @NotNull RSAPublicKey publicKey,
            @NotNull RSAPrivateKey privateKey,
            @NotNull Long expiration
    ) {}
}