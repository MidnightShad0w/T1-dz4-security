package com.danila.userauthorizationservice.adapters.in.rest;

import com.danila.userauthorizationservice.domain.model.User;
import com.danila.userauthorizationservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository users;

    @GetMapping("/me")
    public User me(Authentication auth) {
        String login = auth.getName();
        return users.findByLogin(login).orElseThrow();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> all() {
        return users.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id.toString() == authentication.name || hasAuthority('ADMIN')")
    public User byId(@PathVariable UUID id) {
        return users.findById(id).orElseThrow();
    }
}
