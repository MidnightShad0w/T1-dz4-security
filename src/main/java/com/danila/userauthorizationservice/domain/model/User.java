package com.danila.userauthorizationservice.domain.model;

import java.util.Set;
import java.util.UUID;

public record User(UUID id, String login, String passwordHash, Set<Role> roles, boolean enabled) {
}
