package com.paravar.util;

import com.paravar.AppProperties;
import com.paravar.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.paravar.util.Constants.*;


@Service
@RequiredArgsConstructor
public class JwtUtil {
    
    private final JwtEncoder jwtEncoder;
    private final AppProperties appProperties;
    
    public String generateToken(Long id, String name, String username, Role role) {
        Instant now = Instant.now();
        
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .issuer(appProperties.jwt().issuer())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(appProperties.jwt().expiration()))
                .subject(username);
        

            claimsBuilder.claim(ID_CLAIM, id);
            claimsBuilder.claim(NAME_CLAIM, name);
            claimsBuilder.claim(ROLE_CLAIM, role);


        JwtClaimsSet claims = claimsBuilder.build();
        JwsHeader jws = JwsHeader.with(AppProperties.JwtProperties.ENCRYPTION_ALGORITHM).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jws,claims)).getTokenValue();
    }

    
}