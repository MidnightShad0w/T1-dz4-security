package com.danila.userauthorizationservice.adapters.out.jpa.user;

import com.danila.userauthorizationservice.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByLogin(String login);
}
