package com.spring.basic.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1-1")
public class ScorePageController {

    @GetMapping("/score/score-page")
    public String scorePage() {
        return "score/score-page";
    }
}
