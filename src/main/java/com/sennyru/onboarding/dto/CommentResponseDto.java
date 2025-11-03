package com.sennyru.onboarding.dto;

public record CommentResponseDto(
    Long commentId,
    String email,
    String content
) {
    public static CommentResponseDto of(Long commentId, String email, String content) {
        return new CommentResponseDto(commentId, email, content);
    }
}
