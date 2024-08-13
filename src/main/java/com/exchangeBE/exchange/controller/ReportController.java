package com.exchangeBE.exchange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @GetMapping("/{userId}") // list
    @GetMapping("/{reportId}")
    @PostMapping("/{userId}")
}
