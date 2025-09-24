package com.sennyru.onboarding.dto;

import com.sennyru.onboarding.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String email;
    private String username;

    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
    }
}
