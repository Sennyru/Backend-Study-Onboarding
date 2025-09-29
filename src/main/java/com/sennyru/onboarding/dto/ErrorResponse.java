package com.sennyru.onboarding.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final LocalDateTime time = LocalDateTime.now();
    private final String status;
    private final String message;
    private final String requestURI;

    private ErrorResponse(HttpStatus status, String message, String requestURI) {
        this.status = status.name();
        this.message = message;
        this.requestURI = requestURI;
    }

    public static ErrorResponse of(HttpStatus status, String message, String requestURI) {
        return new ErrorResponse(status, message, requestURI);
    }
}
