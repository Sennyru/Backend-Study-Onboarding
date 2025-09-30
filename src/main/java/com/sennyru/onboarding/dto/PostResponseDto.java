package com.sennyru.onboarding.dto;

public record PostResponseDto(
    Long articleId,
    String email,
    String title,
    String content
) {
    public static PostResponseDto of(Long articleId, String email, String title, String content) {
        return new PostResponseDto(articleId, email, title, content);
    }
}
