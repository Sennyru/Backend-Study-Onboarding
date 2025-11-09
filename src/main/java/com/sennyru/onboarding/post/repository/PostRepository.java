package com.sennyru.onboarding.post.repository;

import com.sennyru.onboarding.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
