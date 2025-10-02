package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CommentDeleteRequestDto(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password
) {
    public static CommentDeleteRequestDto of(String email, String password) {
        return new CommentDeleteRequestDto(email, password);
    }
}
