package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password,

    @NotBlank
    String username
) {
    public static SignupRequestDto of(String email, String password, String username) {
        return new SignupRequestDto(email, password, username);
    }
}
