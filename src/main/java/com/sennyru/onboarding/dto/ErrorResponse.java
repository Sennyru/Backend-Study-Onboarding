package com.sennyru.onboarding.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final LocalDateTime time = LocalDateTime.now();
    private final String status;
    private final String message;
    private final String requestURI;

    @Builder
    public ErrorResponse(HttpStatus status, String message, String requestURI) {
        this.status = status.name();
        this.message = message;
        this.requestURI = requestURI;
    }
}
