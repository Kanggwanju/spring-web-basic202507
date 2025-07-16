package com.spring.basic.score.controller;

import com.spring.basic.score.dto.request.ScoreCreateDto;
import com.spring.basic.score.dto.response.ScoreListResponse;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.ScoreMemoryRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/scores")
@Slf4j
public class ScoreListController {

    private final ScoreMemoryRepository repository;

    public ScoreListController(ScoreMemoryRepository repository) {
        this.repository = repository;
    }


    // 학생 점수 조회
    @GetMapping
    public ResponseEntity<?> scoreList(@RequestParam String sort) {
        
        // Score -> ScoreListResponse 변환
        List<ScoreListResponse> scoreList = repository.findAll().values()
            .stream()
            .map(s -> ScoreListResponse.from(s))
            .sorted(Comparator.comparing(ScoreListResponse::getSum).reversed())
//            .sorted(getComparator(sort))
            .collect(Collectors.toList());



        // 순위 정해줌
        for (int i = 0; i < scoreList.size(); i++) {
            scoreList.get(i).setRank(i+1);
        }
        
        // sort 문자열에 따라 정렬
        List<ScoreListResponse> responseList = scoreList.stream()
            .sorted(getComparator(sort))
            .collect(Collectors.toList());

        return ResponseEntity
            .ok()
            .body(responseList);
    }

    // 학생 점수 생성
    @PostMapping
    public ResponseEntity<?> create(
        @RequestBody @Valid ScoreCreateDto dto
        // 입력값 검증 오류 내용을 갖고있는 객체
        , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) { // 검증 결과 에러가 있다면
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });

            log.warn("회원가입 입력값 오류가 발생함!");
            return ResponseEntity.badRequest().body(errorMap);
        }


        log.info("param - {}", dto);

//        Score score = ScoreCreateDto.from(dto);
//        score.setId(nextId++);
//
//        scoreStore.put(score.getId(), score);

        Score score = ScoreCreateDto.from(dto);
        repository.save(score);

        return ResponseEntity.ok("created student score: " + score);
    }
    
    
    // 학생 점수 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable("id") Long id) {
        Score remove = repository.deleteById(id);
        if (remove == null) {
            return ResponseEntity.badRequest().body(id + "학번은 존재하지 않습니다.");
        }
        return ResponseEntity.ok("Deleted Student Score ID: " + id);
    }




    private Comparator<ScoreListResponse> getComparator(String sort) {

        Comparator<ScoreListResponse> comparing = null;

        switch (sort) {
            case "average":
                comparing = Comparator.comparing(ScoreListResponse::getAvg).reversed();
                break;
            case "name":
                comparing = Comparator.comparing(ScoreListResponse::getMaskingName);
                break;
            default:
                comparing = Comparator.comparing(ScoreListResponse::getId);
        }

        return comparing;
    }


}
