package com.sennyru.onboarding.controller;

import com.sennyru.onboarding.dto.CommentDeleteRequestDto;
import com.sennyru.onboarding.dto.CommentResponseDto;
import com.sennyru.onboarding.dto.CommentUpdateRequestDto;
import com.sennyru.onboarding.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
        @PathVariable Long commentId,
        @Valid @RequestBody CommentUpdateRequestDto requestDto) {
        
        CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
        @PathVariable Long commentId,
        @Valid @RequestBody CommentDeleteRequestDto requestDto) {
        
        commentService.deleteComment(commentId, requestDto);
        return ResponseEntity.noContent().build();
    }
}
