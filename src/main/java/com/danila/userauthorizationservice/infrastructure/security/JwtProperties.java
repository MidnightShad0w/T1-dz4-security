package com.danila.userauthorizationservice.infrastructure.security;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties(prefix = "app.jwt")
@Getter
@Setter
@Slf4j
public class JwtProperties {
    private String secret;
    private Duration accessExpiration;
    private Duration refreshExpiration;

    @PostConstruct
    void logSecret() {
        log.info("JWT secret loaded (length={} chars)", secret.length());
        // log.debug("Secret value = {}", secret);
    }
}
