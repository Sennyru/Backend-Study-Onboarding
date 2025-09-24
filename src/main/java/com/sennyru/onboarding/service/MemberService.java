package com.sennyru.onboarding.service;

import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.dto.SignupRequestDto;
import com.sennyru.onboarding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signup(SignupRequestDto requestDto) {
        String encryptedPassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(encryptedPassword)
                .username(requestDto.getUsername())
                .build();

        return memberRepository.save(member);
    }
}
