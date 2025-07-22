package com.danila.userauthorizationservice.domain.repository;

import com.danila.userauthorizationservice.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByLogin(String login);
    Optional<User> findById(UUID id);

    List<User> findAll();
    User save(User user);
}
