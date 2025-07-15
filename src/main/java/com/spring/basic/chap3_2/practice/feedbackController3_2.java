package com.spring.basic.chap3_2.practice;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v3-2/feedback")
public class feedbackController3_2 {
    private Map<Long, Feedback> feedbackStore = new HashMap<>();
    private long feedbackIdCounter = 1;

    // 전체 조회
    @GetMapping
    public List<Feedback> list() {
        return new ArrayList<>(feedbackStore.values());
    }

    // 회원 등록
    @PostMapping
    public String join(@RequestBody Feedback feedback) {
        feedback.setId(feedbackIdCounter++);
        feedbackStore.put(feedback.getId(), feedback);
        return "Feedback created: " + feedback;
    }


}
