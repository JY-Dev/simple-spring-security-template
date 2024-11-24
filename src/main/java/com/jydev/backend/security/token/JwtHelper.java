package com.jydev.backend.security.token;

import com.jydev.backend.security.config.AuthTokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtHelper {

    private final AuthTokenProperties authTokenProperties;
    private final SecretKey signingKey;

    public JwtHelper(AuthTokenProperties authTokenProperties) {
        this.authTokenProperties = authTokenProperties;
        this.signingKey = Keys.hmacShaKeyFor(authTokenProperties.getSecretKey().getBytes());
    }

    public String issueToken(long userId) {
        try {
            Instant now = Instant.now();
            Date issuedAt = Date.from(now);

            long accessTokenTtl = authTokenProperties.getAccessTokenTtl().toSeconds();
            Instant expirationInstant = now.plus(accessTokenTtl, ChronoUnit.SECONDS);
            Date expirationAt = Date.from(expirationInstant);

            return Jwts.builder()
                    .issuer(ISS)
                    .subject(String.valueOf(userId))
                    .issuedAt(issuedAt)
                    .expiration(expirationAt)
                    .signWith(signingKey)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Failed to issue token", e);
        }
    }

    public boolean isValidToken(String accessToken) {
        if (accessToken == null || accessToken.isBlank()) {
            return false;
        }
        try {
            return !isTokenExpired(accessToken);
        } catch (Exception e) {
            return false;
        }
    }

    public long getUserIdFromToken(String token) {
        String sub = getClaims(token).getSubject();
        return Long.parseLong(sub);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(Date.from(Instant.now()));
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public static String resolveBearerToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    private static final String ISS = "backend";
}