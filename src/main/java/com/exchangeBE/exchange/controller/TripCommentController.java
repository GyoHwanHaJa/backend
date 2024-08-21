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

    @PostMapping
    public CommentDto createComment(@RequestBody CommentDto commentDto,
                                    @RequestParam Long userId,
                                    @RequestParam Long travelPostId) { // 수정된 부분
        return commentService.saveComment(commentDto, userId, travelPostId);
    }

    @PutMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable Long commentId,
                                    @RequestBody CommentDto commentDto,
                                    @RequestParam Long userId,
                                    @RequestParam Long travelPostId) { // 수정된 부분
        return commentService.updateComment(commentId, commentDto, userId, travelPostId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
