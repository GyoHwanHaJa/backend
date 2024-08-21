package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.Trip.CommentDto;
import com.exchangeBE.exchange.service.Trip.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class TripCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<CommentDto> getAllComments() {
        return commentService.findAllComments();
    }

    @GetMapping("/{commentId}")
    public CommentDto getCommentById(@PathVariable Long commentId) {
        return commentService.findCommentById(commentId);
    }

    @PostMapping("/post")
    public CommentDto createComment(@RequestBody CommentDto commentDto) {
        return commentService.saveComment(commentDto, commentDto.getUserId(), commentDto.getTravelPostId());
    }

    @PutMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable Long commentId,
                                    @RequestBody CommentDto commentDto) {
        return commentService.updateComment(commentId, commentDto, commentDto.getUserId(), commentDto.getTravelPostId());
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
