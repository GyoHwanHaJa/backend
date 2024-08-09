package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.entity.Schedule.Tag;
import com.exchangeBE.exchange.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository  = tagRepository;
    }

    public TagDto createTag(TagDto tagDto) {
        Optional<Tag> optionalTag = tagRepository.findByName(tagDto.getName());

        if (optionalTag.isPresent()) {
            tagDto = TagDto.toTagDto(optionalTag.get());
            return tagDto;
        }
        else {
            tagDto = TagDto.toTagDto(tagRepository.save(Tag.toTagEntity(tagDto)));
        }

        return tagDto;
    }
}
