package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.domain.model.Role;
import com.danila.userauthorizationservice.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleJpaAdapter implements RoleRepository {
    private final RoleJpaRepository jpa;
    private final RoleMapper mapper;
    @Override
    public Optional<Role> findByName(String name) {
        return jpa.findByName(name).map(mapper::toDomain);
    }
}
