package com.spring.basic.chap2_5.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v2-5")
public class ResponseController2_5 {

    // 페이지 라우팅 - 특정 뷰 (JSP, thymeleaf)를 포워딩 해주는 것
    @GetMapping("/book-page")
    public String bookPage() {
        return "book-page";
    }



}
