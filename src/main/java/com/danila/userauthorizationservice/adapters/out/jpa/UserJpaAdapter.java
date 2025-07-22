package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.domain.model.User;
import com.danila.userauthorizationservice.domain.repository.UserRepository;
import com.danila.userauthorizationservice.infrastructure.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepository {
    private final UserJpaRepository jpa;
    private final UserMapper mapper;

    @Override
    public Optional<User> findByLogin(String login) {
        return jpa.findByLogin(login).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public User save(User user) {
        UserEntity saved = jpa.save(mapper.toEntity(user));
        return mapper.toDomain(saved);
    }
}
