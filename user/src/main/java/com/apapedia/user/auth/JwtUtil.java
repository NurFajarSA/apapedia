package com.apapedia.user.auth;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import com.apapedia.user.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private String secret_key = "apapediasecret";
    private long accessTokenValidity = 60*60*1000;
    // private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    private final JwtParser jwtParser;

    public JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        claims.put("name", user.getName());
        claims.put("userName", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("address", user.getAddress());
        claims.put("balance", user.getBalance());

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public String resolveToken(HashMap<String, String> headers) {
        String bearerToken = headers.get("authorization");
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

}