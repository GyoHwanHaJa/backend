package com.exchangeBE.exchange.controller.trip;

import com.exchangeBE.exchange.dto.trip.ReplyDto;
import com.exchangeBE.exchange.service.trip.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class TripReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping
    public List<ReplyDto> getAllReplies() {
        return replyService.findAllReplies();
    }

    @GetMapping("/{replyId}")
    public ReplyDto getReplyById(@PathVariable Long replyId) {
        return replyService.findReplyById(replyId);
    }

    @GetMapping("/comment/{commentId}")
    public List<ReplyDto> getRepliesByCommentId(@PathVariable Long commentId) {
        return replyService.findRepliesByCommentId(commentId);
    }

    @PostMapping("/post")
    public ReplyDto createReply(@RequestBody ReplyDto replyDto) {
        return replyService.saveReply(replyDto, replyDto.getCommentId());
    }


    @PutMapping("/{replyId}")
    public ReplyDto updateReply(@PathVariable Long replyId, @RequestBody ReplyDto replyDto) {
        return replyService.updateReply(replyId, replyDto);
    }

    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }
}
