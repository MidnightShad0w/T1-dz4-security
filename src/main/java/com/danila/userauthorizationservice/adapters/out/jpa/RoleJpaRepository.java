package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.infrastructure.persistence.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
