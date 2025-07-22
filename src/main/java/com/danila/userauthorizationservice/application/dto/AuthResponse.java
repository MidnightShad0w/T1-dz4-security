package com.danila.userauthorizationservice.application.dto;

public record AuthResponse(String accessToken, String refreshToken) {
}
