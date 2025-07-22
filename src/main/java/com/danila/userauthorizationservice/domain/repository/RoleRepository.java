package com.danila.userauthorizationservice.domain.repository;

import com.danila.userauthorizationservice.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
