package com.ecommerce.backend.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.models.CpUser;
import com.ecommerce.backend.models.WebUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationMs;

private String createToken(String email, Integer objectId, String objectRole) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", objectId)
                // Usamos roleName para que coincida con tu DTO y tu interface de Angular
                .claim("roleName", (objectRole != null && !objectRole.isEmpty()) ? objectRole : "ROLE_CLIENT") 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateToken(WebUser user) {
        return this.createToken(user.getEmail(), user.getId(), "");
    }

    public String generateToken(CpUser user) {
        return this.createToken(user.getEmail(), user.getId(), user.getRole().getName());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
