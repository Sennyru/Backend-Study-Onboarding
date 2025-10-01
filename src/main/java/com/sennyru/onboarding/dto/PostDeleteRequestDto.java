package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PostDeleteRequestDto(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password
) {
    public static PostDeleteRequestDto of(String email, String password) {
        return new PostDeleteRequestDto(email, password);
    }
}
