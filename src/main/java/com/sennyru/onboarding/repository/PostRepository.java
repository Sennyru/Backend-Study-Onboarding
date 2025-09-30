package com.sennyru.onboarding.repository;

import com.sennyru.onboarding.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
