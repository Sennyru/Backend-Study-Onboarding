package com.sennyru.onboarding.repository;

import com.sennyru.onboarding.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
