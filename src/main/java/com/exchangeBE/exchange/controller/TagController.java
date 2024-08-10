package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.domain.Tag;
import com.exchangeBE.exchange.entity.TagEntity;
import com.exchangeBE.exchange.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        // TagEntity를 Tag로 변환
        return tagService.findAllTags().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }

    // TagEntity를 Tag로 변환하는 메서드
    private Tag convertToDomain(TagEntity tagEntity) {
        Tag tag = new Tag();
        tag.setId(tagEntity.getId());
        tag.setName(tagEntity.getName());
        return tag;
    }

//    @PostMapping
//    public Tag createTag(@RequestBody Tag tag) {
//        return tagService.saveTag(tag);
//    }
}
