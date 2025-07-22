package com.danila.userauthorizationservice.adapters.in.rest;

import com.danila.userauthorizationservice.application.dto.*;
import com.danila.userauthorizationservice.application.service.AuthenticationService;
import com.danila.userauthorizationservice.application.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService auth;
    private final RegistrationService register;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegistrationRequest dto) {
        return register.register(dto);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest dto) {
        return auth.login(dto);
    }

    @PostMapping("/refresh")
    public AuthResponse login(@Valid @RequestBody RefreshRequest dto) {
        return auth.refresh(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest dto) {
        auth.logout(dto.refreshToken());
        return ResponseEntity.noContent().build();
    }
}
