package com.danila.userauthorizationservice.domain.repository;

import com.danila.userauthorizationservice.domain.model.RefreshToken;

import java.util.Optional;

public interface TokenRepository {
    RefreshToken save(RefreshToken token);

    Optional<RefreshToken> findByToken(String token);

    void revoke(RefreshToken token);
}
