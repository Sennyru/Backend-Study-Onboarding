package com.sennyru.onboarding.controller;

import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.dto.MemberResponseDto;
import com.sennyru.onboarding.dto.SignupRequestDto;
import com.sennyru.onboarding.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
        Member savedMember = memberService.signup(requestDto);
        MemberResponseDto responseDto = MemberResponseDto.from(savedMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
