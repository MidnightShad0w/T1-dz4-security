package com.danila.userauthorizationservice.adapters.out.jpa;

import com.danila.userauthorizationservice.domain.model.Role;
import com.danila.userauthorizationservice.domain.model.User;
import com.danila.userauthorizationservice.infrastructure.persistence.RoleEntity;
import com.danila.userauthorizationservice.infrastructure.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleMapper roleMapper;
    public User toDomain(UserEntity e) {
        if (e == null) return null;
        Set<Role> roles = e.getRoles()
                .stream()
                .map(RoleEntity::getName)
                .map(Role::valueOf)
                .collect(Collectors.toSet());

        return new User(
                e.getId(),
                e.getLogin(),
                e.getPassword(),
                roles,
                e.isEnabled()
        );
    }

    public UserEntity toEntity(User d) {
        if (d == null) return null;

        UserEntity e = new UserEntity();
        e.setId(d.id());
        e.setLogin(d.login());
        e.setPassword(d.passwordHash());
        e.setEnabled(d.enabled());
        e.setRoles(
                d.roles().stream()
                        .map(roleMapper::toEntity)
                        .collect(Collectors.toSet())
        );
        return e;
    }
}
