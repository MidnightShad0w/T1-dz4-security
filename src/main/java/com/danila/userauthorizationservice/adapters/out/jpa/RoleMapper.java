package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.domain.model.Role;
import com.danila.userauthorizationservice.infrastructure.persistence.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleJpaRepository repo;

    public Role toDomain(RoleEntity e) {
        return e == null ? null : Role.valueOf(e.getName());
    }

    public RoleEntity toEntity(Role role) {
        if (role == null) return null;
        return repo.findByName(role.name())
                .orElseThrow(() ->
                        new IllegalStateException("Role "+role+" not found in DB"));
    }
}
