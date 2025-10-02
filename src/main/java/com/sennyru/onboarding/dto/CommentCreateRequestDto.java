package com.sennyru.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CommentCreateRequestDto(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password,

    @NotBlank
    String content
) {
    public static CommentCreateRequestDto of(String email, String password, String content) {
        return new CommentCreateRequestDto(email, password, content);
    }
}
