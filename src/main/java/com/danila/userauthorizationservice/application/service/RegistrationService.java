package com.danila.userauthorizationservice.application.service;

import com.danila.userauthorizationservice.application.dto.AuthResponse;
import com.danila.userauthorizationservice.application.dto.RegistrationRequest;
import com.danila.userauthorizationservice.domain.model.Role;
import com.danila.userauthorizationservice.domain.model.User;
import com.danila.userauthorizationservice.domain.repository.TokenRepository;
import com.danila.userauthorizationservice.domain.repository.UserRepository;
import com.danila.userauthorizationservice.infrastructure.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository users;
    private final TokenRepository tokens;
    private final PasswordEncoder encoder;
    private final JwtProvider jwt;

    public AuthResponse register(RegistrationRequest request) {
        users.findByLogin(request.login()).ifPresent(u -> {
            throw new IllegalArgumentException("Login taken");
        });

        User saved = users.save(new User(null, request.login(), encoder.encode(request.password()), EnumSet.of(Role.GUEST), true));

        String access = jwt.generateAccessToken(saved);
        String refresh = jwt.generateRefreshToken(saved);

        tokens.save(jwt.toRefreshDomain(refresh, saved.id()));
        return new AuthResponse(access, refresh);
    }
}
