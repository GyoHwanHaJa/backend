package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.domain.TripPost;
import com.exchangeBE.exchange.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/posts")
    public List<TripPost> getMainPagePosts() {
        return mainService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public TripPost getPostById(@PathVariable Long id) {
        return mainService.getPostById(id);
    }
}
