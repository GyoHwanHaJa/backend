package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.TripPostDto;
import com.exchangeBE.exchange.entity.TopicEntity;
import com.exchangeBE.exchange.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/travels")
    public List<TripPostDto> getMainPagePosts() {
        return mainService.getAllPosts();
    }

    @GetMapping("/travels/{id}")
    public TripPostDto getPostById(@PathVariable Long id) {
        return mainService.getPostById(id);
    }

    // 새로운 POST 메소드 추가
    @PostMapping("/travels/{id}/selectTopic")
    public TripPostDto selectAndSaveTopic(@PathVariable Long id, @RequestParam TopicEntity selectedTopic) {
        // ID로 게시물을 조회
        TripPostDto post = mainService.getPostById(id);

        // 조회된 게시물의 주제 설정
        post.setTopic(selectedTopic);

        // 설정된 게시물을 저장
        return mainService.savePost(post);
    }
}
