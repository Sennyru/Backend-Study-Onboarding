package com.sennyru.onboarding.service;

import com.sennyru.onboarding.dto.PostDeleteRequestDto;
import com.sennyru.onboarding.dto.PostUpdateRequestDto;
import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.domain.Post;
import com.sennyru.onboarding.dto.PostCreateRequestDto;
import com.sennyru.onboarding.dto.PostResponseDto;
import com.sennyru.onboarding.repository.MemberRepository;
import com.sennyru.onboarding.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PostResponseDto createPost(PostCreateRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.email())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(requestDto.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Post post = Post.create(requestDto.title(), requestDto.content(), member);
        Post savedPost = postRepository.save(post);

        return PostResponseDto.of(
            savedPost.getId(),
            savedPost.getMember().getEmail(),
            savedPost.getTitle(),
            savedPost.getContent()
        );
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        Member member = memberRepository.findByEmail(requestDto.email())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(requestDto.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (!Objects.equals(post.getMember().getId(), member.getId())) {
            throw new IllegalStateException("게시물 수정 권한이 없습니다.");
        }

        post.update(requestDto.title(), requestDto.content());

        return PostResponseDto.of(
            post.getId(),
            post.getMember().getEmail(),
            post.getTitle(),
            post.getContent()
        );
    }

    @Transactional
    public void deletePost(Long postId, PostDeleteRequestDto requestDto) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        Member member = memberRepository.findByEmail(requestDto.email())
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(requestDto.password(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if (!Objects.equals(post.getMember().getId(), member.getId())) {
            throw new IllegalStateException("게시물 삭제 권한이 없습니다.");
        }

        postRepository.delete(post);
    }
}
