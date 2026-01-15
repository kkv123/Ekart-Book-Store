package com.ekart.bookStore.config;

//-------------------------------------------Here i am generating Token for the user--------------------------------------------

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

    import static com.ekart.bookStore.config.JwtAuthenticationFilter.SECRET_KEY;

public class TokenGenerator {

    private static final Key key= Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    public static String generateToken(String role) {
        return Jwts.builder()
                .setSubject("response-token")
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}