package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.entity.TagEntity;
import com.exchangeBE.exchange.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<TagEntity> findAllTags() {
        return tagRepository.findAll();
    }

    public TagEntity saveTag(TagEntity tag) {
        return tagRepository.save(tag);
    }
}
