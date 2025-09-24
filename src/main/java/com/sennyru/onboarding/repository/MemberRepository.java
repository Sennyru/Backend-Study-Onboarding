package com.sennyru.onboarding.repository;

import com.sennyru.onboarding.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
