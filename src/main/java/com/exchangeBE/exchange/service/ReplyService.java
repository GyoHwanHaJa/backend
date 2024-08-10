package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.ReplyDto;
import com.exchangeBE.exchange.entity.CommentEntity;
import com.exchangeBE.exchange.entity.ReplyEntity;
import com.exchangeBE.exchange.repository.CommentRepository;
import com.exchangeBE.exchange.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CommentRepository commentRepository;

    public ReplyDto saveReply(ReplyDto replyDto, Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        ReplyEntity reply = new ReplyEntity();
        reply.setContent(replyDto.getContent());
        reply.setComment(comment);
        reply.setCreatedAt(LocalDateTime.now());
        reply.setUpdatedAt(LocalDateTime.now());

        replyRepository.save(reply);

        return convertToDto(reply);
    }

    public ReplyDto updateReply(Long replyId, ReplyDto replyDto) {
        ReplyEntity reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        reply.setContent(replyDto.getContent());
        reply.setUpdatedAt(LocalDateTime.now());

        replyRepository.save(reply);

        return convertToDto(reply);
    }

    public List<ReplyDto> findAllReplies() {
        return replyRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReplyDto> findRepliesByCommentId(Long commentId) {
        return replyRepository.findByCommentId(commentId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ReplyDto findReplyById(Long replyId) {
        ReplyEntity reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found"));
        return convertToDto(reply);
    }

    public void deleteReply(Long replyId) {
        ReplyEntity reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new RuntimeException("Reply not found"));
        replyRepository.delete(reply);
    }

    // Entity를 DTO로 변환하는 유틸리티 메서드
    private ReplyDto convertToDto(ReplyEntity reply) {
        ReplyDto dto = new ReplyDto();
        dto.setId(reply.getId());
        dto.setContent(reply.getContent());
        dto.setCommentId(reply.getComment().getId());
        dto.setCreatedAt(reply.getCreatedAt());
        dto.setUpdatedAt(reply.getUpdatedAt());
        return dto;
    }
}
