package com.paravar;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "app.jwt")
public record AppProperties(
        @NotNull RSAPublicKey publicKey, @NotNull RSAPrivateKey privateKey,
        @NotNull Long accessTokenTimeToLive, @NotNull Long refreshTokenTimeToLive
) {
}
