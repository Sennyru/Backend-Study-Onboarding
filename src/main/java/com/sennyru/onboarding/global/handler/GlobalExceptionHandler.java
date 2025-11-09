package com.sennyru.onboarding.global.handler;

import com.sennyru.onboarding.global.exception.DomainException;
import com.sennyru.onboarding.global.handler.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(ex.getStatus(), ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
