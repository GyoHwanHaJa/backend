package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.ReplyDto;
import com.exchangeBE.exchange.entity.ReplyEntity;
import com.exchangeBE.exchange.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public List<ReplyDto> findRepliesByCommentId(Long commentId) {
        return replyRepository.findByCommentId(commentId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ReplyDto> findAllReplies() {
        return replyRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public ReplyDto saveReply(ReplyDto replyDto) {
        ReplyEntity replyEntity = convertToEntity(replyDto);
        return convertToDto(replyRepository.save(replyEntity));
    }

    public ReplyDto updateReply(Long replyId, ReplyDto replyDto) {
        ReplyEntity existingReply = replyRepository.findById(replyId).orElse(null);
        if (existingReply != null) {
            existingReply.setContent(replyDto.getContent());
            existingReply.setTimestamp(replyDto.getTimestamp());
            return convertToDto(replyRepository.save(existingReply));
        }
        return null;
    }

    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    private ReplyDto convertToDto(ReplyEntity entity) {
        ReplyDto dto = new ReplyDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setTimestamp(entity.getTimestamp());
        dto.setCommentId(entity.getComment().getId());
        return dto;
    }

    private ReplyEntity convertToEntity(ReplyDto dto) {
        ReplyEntity entity = new ReplyEntity();
        entity.setContent(dto.getContent());
        entity.setTimestamp(dto.getTimestamp());
        // CommentEntity 설정 필요 시 추가
        return entity;
    }
}
