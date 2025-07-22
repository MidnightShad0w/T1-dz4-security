package com.danila.userauthorizationservice.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank String login, @NotBlank String password) {
}
