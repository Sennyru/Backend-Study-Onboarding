package com.sennyru.onboarding.controller;

import com.sennyru.onboarding.dto.CommentCreateRequestDto;
import com.sennyru.onboarding.dto.CommentResponseDto;
import com.sennyru.onboarding.service.CommentService;
import com.sennyru.onboarding.dto.PostDeleteRequestDto;
import com.sennyru.onboarding.dto.PostUpdateRequestDto;
import com.sennyru.onboarding.dto.PostCreateRequestDto;
import com.sennyru.onboarding.dto.PostResponseDto;
import com.sennyru.onboarding.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto requestDto) {
        PostResponseDto responseDto = postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @Valid @RequestBody PostUpdateRequestDto requestDto) {
        PostResponseDto responseDto = postService.updatePost(postId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @Valid @RequestBody PostDeleteRequestDto requestDto) {
        postService.deletePost(postId, requestDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createCommentForPost(
        @PathVariable Long postId,
        @Valid @RequestBody CommentCreateRequestDto requestDto) {
        
        CommentResponseDto responseDto = commentService.createComment(postId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
