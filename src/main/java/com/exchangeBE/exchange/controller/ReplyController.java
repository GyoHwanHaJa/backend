package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.domain.Reply;
import com.exchangeBE.exchange.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping
    public List<Reply> getAllReplies() {
        return replyService.findAllReplies();
    }

    @PostMapping
    public Reply createReply(@RequestBody Reply reply) {
        return replyService.saveReply(reply);
    }
}
