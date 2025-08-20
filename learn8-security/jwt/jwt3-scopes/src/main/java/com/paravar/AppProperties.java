package com.paravar;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

@ConfigurationProperties(prefix = "app.jwt")
public record AppProperties(String secret, String issuer, Long expiration) {
     static final MacAlgorithm ENCRYPTION_ALGORITHM = MacAlgorithm.HS256;
}
