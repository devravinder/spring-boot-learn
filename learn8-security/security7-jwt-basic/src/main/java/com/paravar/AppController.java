package com.paravar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AppController {

    @Autowired
    JwtEncoder encoder;

    //@PostMapping("/token")
    //@GetMapping("/token")
    @RequestMapping(value = "/token", method = {RequestMethod.GET, RequestMethod.POST})
    public String token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    @GetMapping("/")
    Map<String, String> home(Authentication authentication){
        var map = new HashMap<String, String>();
        map.put("greet", "Hello");
        map.put("message", "How are you?");
        map.put("name", authentication.getName());
        return map;
    }

    @GetMapping("/secure")
    Map<String, String> secure(){

        var map = new HashMap<String, String>();
        map.put("greet", "Hello");
        map.put("message", "How are you?");
        return map;
    }

}