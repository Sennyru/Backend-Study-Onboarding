package com.sennyru.onboarding.service;

import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.dto.MemberResponseDto;
import com.sennyru.onboarding.dto.SignupRequestDto;
import com.sennyru.onboarding.exception.EmailAlreadyExistsException;
import com.sennyru.onboarding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.sennyru.onboarding.exception.InvalidPasswordException;
import com.sennyru.onboarding.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseDto signup(SignupRequestDto requestDto) {
        String encryptedPassword = passwordEncoder.encode(requestDto.password());
        Member member = Member.create(requestDto.email(), encryptedPassword, requestDto.username());

        try {
            Member savedMember = memberRepository.save(member);
            return MemberResponseDto.of(savedMember.getEmail(), savedMember.getUsername());
        } catch (DataIntegrityViolationException e) {
            throw new EmailAlreadyExistsException("이미 사용 중인 이메일입니다.");
        }
    }
    
    
    /**
     * 이메일이 가입되어 있고 비밀번호가 일치하는지 검증한다.
     * @param email 사용자 이메일
     * @param password 평문 비밀번호
     * @return 검증된 Member 엔티티
     * @throws MemberNotFoundException 가입되지 않은 이메일일 경우
     * @throws InvalidPasswordException 비밀번호가 일치하지 않을 경우
     */
    public Member authenticateAndFindMember(String email, String password) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new MemberNotFoundException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}
