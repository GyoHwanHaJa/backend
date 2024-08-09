package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.domain.Tag;
import com.exchangeBE.exchange.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.findAllTags();
    }

//    @PostMapping
//    public Tag createTag(@RequestBody Tag tag) {
//        return tagService.saveTag(tag);
//    }
}
