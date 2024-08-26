package com.example.jwtsecurity.util;

import jdk.internal.org.jline.reader.LineReaderBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.DoubleStream;

@Component
public class JwtTokenUtil {

    private final String SECRET_KEY = "mySecretKey"; // Cambia esto por una clave secreta más segura

    public String generateToken(UserDetails userDetails, DoubleStream Jwts) {
        Object SignatureAlgorithm = null;
        return Jwts.builder()
                .setClaims(new Claims())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        LineReaderBuilder Jwts = null;
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
