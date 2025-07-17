package com.spring.basic.score.controller;

import com.spring.basic.score.dto.request.ScoreCreateRequest;
import com.spring.basic.score.dto.response.ScoreDetailResponse;
import com.spring.basic.score.dto.response.ScoreListResponse;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.ScoreMemoryRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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
    public ResponseEntity<?> getScoreList(
        @RequestParam(defaultValue = "id") String sort
    ) {

        log.info("/api/v1/scores GET !");
        log.debug("param: sort - {}", sort);
        
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
    public ResponseEntity<?> createScore(
        @RequestBody @Valid ScoreCreateRequest dto
        // 입력값 검증 오류 내용을 갖고있는 객체
        , BindingResult bindingResult
    ) {

        log.info("/api/v1/scores POST !");
        log.info("param - {}", dto);

        if (bindingResult.hasErrors()) { // 검증 결과 에러가 있다면
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });

            log.warn("회원가입 입력값 오류가 발생함!");
            return ResponseEntity.badRequest().body(errorMap);
        }




//        Score score = ScoreCreateDto.from(dto);
//        score.setId(nextId++);
//
//        scoreStore.put(score.getId(), score);
        
        // 실제로 데이터베이스에 저장
        Score score = ScoreCreateRequest.from(dto);
        Score savedScore = repository.save(score);

        return ResponseEntity.ok().body("성적 정보 생성 완료 - " + savedScore);
    }
    
    
    // 성적 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable("id") Long id) {
        Score remove = repository.deleteById(id);
        if (remove == null) {
            return ResponseEntity.badRequest().body(id + "학번은 존재하지 않습니다.");
        }
        return ResponseEntity.ok("Deleted Student Score ID: " + id);
    }

    // 학생 점수 detail 반환
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailScore(@PathVariable("id") Long id) {
        
        // 저장소 전체 데이터 List로 가져옴
        List<Score> allScores = new ArrayList<>(repository.findAll().values());

        // 파라미터의 id와 동일한 Score 객체
        Score targetScore = allScores.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElse(null);

        if (targetScore == null) {
            return ResponseEntity.status(400).body(id + "학번의 사람은 존재하지 않습니다.");
        }

        // 전체를 정렬하여 랭킹 계산
        List<Score> sortedScores = allScores.stream()
            .sorted(Comparator.comparing(Score::getTotal).reversed())
            .collect(Collectors.toList());

        // 순위, 총 인원 수
        int rank = 1;
        for (int i = 0; i < sortedScores.size(); i++) {
            if (sortedScores.get(i).getId().equals(id)) {
                rank = i + 1;
                break;
            }
        }
        int totalCount = sortedScores.size();

        // DTO로 변환
        ScoreDetailResponse response = ScoreDetailResponse.from(targetScore);
        response.setRank(rank);
        response.setTotalCount(totalCount);

        return ResponseEntity.ok().body(response);
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
