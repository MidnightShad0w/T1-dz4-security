package com.danila.userauthorizationservice.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(@NotBlank String login,
                                  @NotBlank String password,
                                  @Email String email) {
}
