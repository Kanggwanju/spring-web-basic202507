package com.spring.basic.score.controller;

import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.ScoreMemoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/score")
public class ScorePageController {

    private final ScoreMemoryRepository repository;

    public ScorePageController(ScoreMemoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/page")
    public String scorePage() {
        return "score/score-page";
    }

    @GetMapping("/{id}")
    public String detailPage(@PathVariable("id") Long id, Model model) {
        Score score = repository.findById(id);
        if (score == null) {
            return "redirect:/score/score-page";
        }

        // JSP에게 특정 데이터(id)를 전송
        model.addAttribute("stuId", id);

        return "score/score-detail";
    }

}


