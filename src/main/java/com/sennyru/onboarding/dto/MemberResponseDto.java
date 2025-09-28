package com.sennyru.onboarding.dto;

import com.sennyru.onboarding.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final String email;
    private final String username;

    private MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
    }

    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(member);
    }
}