package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.domain.model.RefreshToken;
import com.danila.userauthorizationservice.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenJpaAdapter implements TokenRepository {
    private final TokenJpaRepository jpa;
    private final TokenMapper mapper;

    @Override
    public RefreshToken save(RefreshToken token) {
        return mapper.toDomain(jpa.save(mapper.toEntity(token)));
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return jpa.findByToken(token).map(mapper::toDomain);
    }

    @Override
    public void revoke(RefreshToken token) {
        var e = mapper.toEntity(token);
        e.setRevoked(true);
        jpa.save(e);
    }
}
