package com.paravar;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "app.jwt")
public record AppProperties(String secret, String issuer, Long expiration, @NotNull RSAPublicKey publicKey, @NotNull RSAPrivateKey privateKey) {
     static final SignatureAlgorithm ENCRYPTION_ALGORITHM = SignatureAlgorithm.RS256;
}
