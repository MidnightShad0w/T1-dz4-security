package com.danila.userauthorizationservice.infrastructure.security;

import com.danila.userauthorizationservice.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtProvider jwt;
    private final UserRepository users;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .verifyWith(jwt.getKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                UUID userId = UUID.fromString(claims.getSubject());

//                @SuppressWarnings("unchecked")
                var roles = (Iterable<String>) claims.get("roles");
                var authorities = StreamSupport.stream(roles.spliterator(), false)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

                users.findById(userId).ifPresent(u -> {
                    var auth = new UsernamePasswordAuthenticationToken(
                            u.login(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                });

            } catch (Exception ignored) {
            }
        }
        filterChain.doFilter(request, response);
    }
}
