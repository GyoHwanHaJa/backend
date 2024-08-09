package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.ReplyDto;
import com.exchangeBE.exchange.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments/{commentId}/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping
    public List<ReplyDto> getRepliesByCommentId(@PathVariable Long commentId) {
        return replyService.findRepliesByCommentId(commentId);
    }

    @PostMapping
    public ReplyDto createReply(@PathVariable Long commentId, @RequestBody ReplyDto replyDto) {
        replyDto.setCommentId(commentId);
        return replyService.saveReply(replyDto);
    }

    @PutMapping("/{replyId}")
    public ReplyDto updateReply(@PathVariable Long commentId, @PathVariable Long replyId, @RequestBody ReplyDto replyDto) {
        replyDto.setCommentId(commentId);
        return replyService.updateReply(replyId, replyDto);
    }

    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable Long commentId, @PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }
}
