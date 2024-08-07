package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.entity.Schedule.Tag;
import com.exchangeBE.exchange.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository  = tagRepository;
    }

    public Tag createTag(TagDto tagDto) {
        Tag tag = Tag.toTagEntity(tagDto);
        return tagRepository.save(tag);
    }
}
