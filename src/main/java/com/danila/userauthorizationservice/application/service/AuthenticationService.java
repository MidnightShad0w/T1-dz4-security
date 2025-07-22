package com.danila.userauthorizationservice.application.service;

import com.danila.userauthorizationservice.application.dto.AuthRequest;
import com.danila.userauthorizationservice.application.dto.AuthResponse;
import com.danila.userauthorizationservice.domain.model.User;
import com.danila.userauthorizationservice.domain.repository.TokenRepository;
import com.danila.userauthorizationservice.domain.repository.UserRepository;
import com.danila.userauthorizationservice.infrastructure.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository users;
    private final TokenRepository tokens;
    private final JwtProvider jwt;
    private final PasswordEncoder encoder;

    public AuthResponse handle(AuthRequest request) {
        User user = users.findByLogin(request.login())
                .filter(u -> encoder.matches(request.password(), u.passwordHash()))
                .orElseThrow(() -> new IllegalArgumentException("Bad credentials"));

        String access = jwt.generateAccessToken(user);
        String refresh = jwt.generateRefreshToken(user);
        tokens.save(jwt.toRefreshDomain(refresh, user.id()));
        return new AuthResponse(access, refresh);
    }
}
