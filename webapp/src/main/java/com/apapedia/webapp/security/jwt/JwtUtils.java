package com.apapedia.webapp.security.jwt;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtUtils {

    public String getClaimsFromJwtToken(String token){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        System.out.println(payload);
        return payload;
    }
}
