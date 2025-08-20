package com.paravar;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtUtil {
    
    private final JwtEncoder jwtEncoder;
    private final AppProperties appProperties;

    @PostConstruct
    public void generateJwt() throws Exception {
        System.out.println("default token: "+generateToken("user"));
    }
    
    public String generateToken(String username, String... roles) {
        Instant now = Instant.now();
        
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .issuer(appProperties.issuer())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(appProperties.expiration()))
                .subject(username);
        
        // Add roles as scope claim
        if (roles.length > 0) {
            claimsBuilder.claim("scope", String.join(" ", roles));
        }

        JwtClaimsSet claims = claimsBuilder.build();
        JwsHeader jws = JwsHeader.with(AppProperties.ENCRYPTION_ALGORITHM).build();


        return jwtEncoder.encode(JwtEncoderParameters.from(jws,claims)).getTokenValue();
    }

    
}