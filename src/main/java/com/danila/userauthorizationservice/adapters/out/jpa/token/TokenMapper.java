package com.danila.userauthorizationservice.adapters.out.jpa.token;

import com.danila.userauthorizationservice.domain.model.RefreshToken;
import com.danila.userauthorizationservice.infrastructure.persistence.RefreshTokenEntity;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {
    public RefreshToken toDomain(RefreshTokenEntity e) {
        if (e == null) return null;
        return new RefreshToken(
                e.getId(),
                e.getUser().getId(),
                e.getToken(),
                e.getExpiresAt(),
                e.isRevoked()
        );
    }

    public RefreshTokenEntity toEntity(RefreshToken d) {
        if (d == null) return null;
        RefreshTokenEntity e = new RefreshTokenEntity();
        e.setId(d.id());
        e.setToken(d.token());
        e.setExpiresAt(d.expiresAt());
        e.setRevoked(d.revoked());
        return e;
    }
}
