package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.domain.model.RefreshToken;
import com.danila.userauthorizationservice.domain.repository.TokenRepository;
import com.danila.userauthorizationservice.infrastructure.persistence.RefreshTokenEntity;
import com.danila.userauthorizationservice.infrastructure.persistence.UserEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenJpaAdapter implements TokenRepository {
    private final TokenJpaRepository jpa;
    private final TokenMapper mapper;
    private final EntityManager em;

    @Override
    public RefreshToken save(RefreshToken d) {
        UserEntity userRef = em.getReference(UserEntity.class, d.userId());

        RefreshTokenEntity e = new RefreshTokenEntity();
        e.setId(d.id());
        e.setToken(d.token());
        e.setExpiresAt(d.expiresAt());
        e.setRevoked(d.revoked());
        e.setUser(userRef);

        return mapper.toDomain(jpa.save(e));
    }


    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return jpa.findByToken(token).map(mapper::toDomain);
    }

    @Override
    public void revoke(RefreshToken d) {
        jpa.findById(d.id()).ifPresent(e -> {
            e.setRevoked(true);
            jpa.save(e);
        });
    }
}
