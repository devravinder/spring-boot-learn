package com.paravar;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.validation.annotation.Validated;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Validated
@ConfigurationProperties(prefix = "project-management-app")
public record AppProperties(
        @NotNull String someKey,
        @NotNull JwtProperties jwt
) {
    public record JwtProperties(
            @NotNull RSAPublicKey publicKey,
            @NotNull RSAPrivateKey privateKey,
            @NotNull Long expiration,
            @NotNull String issuer
    ) {
        public static final SignatureAlgorithm ENCRYPTION_ALGORITHM = SignatureAlgorithm.RS256;
    }
}