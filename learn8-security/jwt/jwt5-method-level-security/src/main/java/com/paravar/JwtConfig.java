package com.paravar;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    private final AppProperties properties;

    private SecretKey macKey() {
        byte[] bytes = properties.secret().getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(bytes, AppProperties.ENCRYPTION_ALGORITHM.getName());
    }

    @Bean
    JwtEncoder jwtEncoder() {
        // ImmutableSecret uses the same symmetric key for HS256
        return new NimbusJwtEncoder(new ImmutableSecret<>(macKey()));
    }

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder decoder =  NimbusJwtDecoder.withSecretKey(macKey())
                .macAlgorithm(AppProperties.ENCRYPTION_ALGORITHM)
                .build();

        // Combine default validators with issuer validation and custom clock skew
        OAuth2TokenValidator<Jwt> defaultValidator = JwtValidators.createDefaultWithIssuer(properties.issuer()); // validates issuer

        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                defaultValidator,
                new JwtTimestampValidator(Duration.ofSeconds(2) // Reduce clock skew to 2 seconds
                ));

        decoder.setJwtValidator(validator);

        return  decoder;
    }
}
