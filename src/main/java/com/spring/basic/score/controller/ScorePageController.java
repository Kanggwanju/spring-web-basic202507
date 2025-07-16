package com.spring.basic.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1-1")
public class ScorePageController {

    @GetMapping("/score/page")
    public String scorePage() {
        return "score/score-page";
    }

    @GetMapping("/score/{id}")
    public String detailPage(@PathVariable("id") Long id, Model model) {


        return "";
    }

}


