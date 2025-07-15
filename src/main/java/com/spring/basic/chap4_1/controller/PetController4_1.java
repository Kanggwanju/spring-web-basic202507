package com.spring.basic.chap4_1.controller;

import com.spring.basic.chap4_1.entity.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chap4-1")
public class PetController4_1 {

    private static long nextNo = 1;

    private List<Pet> petList = new ArrayList<>();

    public PetController4_1() {
        petList.add(Pet.builder()
                        .id(nextNo++)
                        .age(3)
                        .name("뽀삐")
                        .kind("불독")
                        .injection(true)
                        .build());
        petList.add(Pet.builder()
                        .id(nextNo++)
                        .age(2)
                        .name("멍멍")
                        .kind("말라뮤트")
                        .injection(false)
                        .build());
        petList.add(Pet.builder()
                        .id(nextNo++)
                        .age(5)
                        .name("깽깽")
                        .kind("치와와")
                        .injection(true)
                        .build());
    }

    // pet.jsp를 열어줘 (뷰 포워딩) - 페이지 라우팅
    @GetMapping("/pet-page")
    public String petPage(Model model) {
        // 서버사이드 렌더링을 위해 JSP 파일에게 리스트를 전달
        model.addAttribute("petList", petList);
        return "chap4-1/pet";
    }
}
