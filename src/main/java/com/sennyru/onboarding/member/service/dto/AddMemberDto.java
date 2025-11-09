package com.sennyru.onboarding.member.service.dto;

public record AddMemberDto (
    String email,
    String password,
    String username
) {
    public static AddMemberDto of(String email, String password, String username) {
        return new AddMemberDto(email, password, username);
    }
}
