package com.danila.userauthorizationservice.infrastructure.security;

import com.danila.userauthorizationservice.domain.model.RefreshToken;
import com.danila.userauthorizationservice.domain.model.Role;
import com.danila.userauthorizationservice.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private final SecretKey key;
    private final JwtProperties props;

    public JwtProvider(JwtProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(props.getSecret()));
    }

    public String generateAccessToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.id().toString())
                .claim("roles", roleNames(user.roles()))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(props.getAccessExpiration())))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.id().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(props.getRefreshExpiration())))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public SecretKey getKey() {
        return key;
    }

    private static Set<String> roleNames(Set<Role> roles) {
        return roles.stream().map(Enum::name).collect(Collectors.toSet());
    }

    public RefreshToken toRefreshDomain(String token, UUID userId) {
        return new RefreshToken(null, userId, token, Instant.now().plus(props.getRefreshExpiration()), false);
    }
}
