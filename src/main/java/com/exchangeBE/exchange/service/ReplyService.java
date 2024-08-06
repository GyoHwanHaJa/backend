package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.domain.Reply;
import com.exchangeBE.exchange.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> findAllReplies() {
        return replyRepository.findAll();
    }

    public Reply saveReply(Reply reply) {
        return replyRepository.save(reply);
    }
}
