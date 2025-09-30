package com.sennyru.onboarding.dto;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime time,
    String status,
    String message,
    String requestURI
) {
    public static ErrorResponse of(HttpStatus status, String message, String requestURI) {
        return new ErrorResponse(LocalDateTime.now(), status.name(), message, requestURI);
    }
}
