package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.CommentRequestDTO;
import com.exchangeBE.exchange.dto.CommentResponseDTO;
import com.exchangeBE.exchange.service.Community.CommunityCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/comments")
public class CommentController {
    @Autowired
    private CommunityCommentService communityCommentService;

    @PostMapping("/{boardId}")
    public CommentResponseDTO addComment(@PathVariable Long boardId, @RequestBody CommentRequestDTO request) {
        return communityCommentService.addComment(boardId, request);
    }

    @PostMapping("/{boardId}/reply")
    public CommentResponseDTO addReply(@PathVariable Long boardId, @RequestBody CommentRequestDTO request) {
        return communityCommentService.addReply(boardId, request);
    }

    @GetMapping("/board/{boardId}")
    public List<CommentResponseDTO> getComments(@PathVariable Long boardId) {
        return communityCommentService.getComments(boardId);
    }
}