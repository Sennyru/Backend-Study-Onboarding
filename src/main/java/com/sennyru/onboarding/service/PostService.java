package com.sennyru.onboarding.service;

import com.sennyru.onboarding.domain.Member;
import com.sennyru.onboarding.domain.Post;
import com.sennyru.onboarding.dto.PostCreateRequestDto;
import com.sennyru.onboarding.dto.PostDeleteRequestDto;
import com.sennyru.onboarding.dto.PostResponseDto;
import com.sennyru.onboarding.dto.PostUpdateRequestDto;
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
        Member member = authenticateMember(requestDto.email(), requestDto.password());

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
        Member member = authenticateMember(requestDto.email(), requestDto.password());
        Post post = findPostAndValidateOwnership(postId, member);

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
        Member member = authenticateMember(requestDto.email(), requestDto.password());
        Post post = findPostAndValidateOwnership(postId, member);

        postRepository.delete(post);
    }
    
    
    /**
     * 이메일이 가입되어 있고 비밀번호가 일치한지 검증합니다.
     * @param email 사용자 이메일
     * @param password 평문 비밀번호
     * @return 검증된 Member 엔티티
     * @throws IllegalArgumentException 가입되지 않은 이메일이거나 비밀번호가 일치하지 않을 경우
     */
    private Member authenticateMember(String email, String password) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    /**
     * 게시물이 존재하는지 확인하고, 사용자가 게시물의 작성자인지 검증합니다.
     * @param postId 확인할 게시물의 ID
     * @param member (인증된) 사용자 Member 엔티티
     * @return 검증된 Post 엔티티
     * @throws IllegalArgumentException 게시물이 존재하지 않을 경우
     * @throws IllegalStateException 게시물에 대한 권한이 없을 경우
     */
    private Post findPostAndValidateOwnership(Long postId, Member member) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        if (!Objects.equals(post.getMember().getId(), member.getId())) {
            throw new IllegalStateException("게시물에 대한 권한이 없습니다.");
        }
        return post;
    }
}
