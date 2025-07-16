package com.spring.basic.score.repository;

import com.spring.basic.score.entity.Score;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScoreMemoryRepository {

    private final Map<Long, Score> scoreStore = new HashMap<>();
    private Long nextId = 1L;

    public ScoreMemoryRepository() {
        Score s1 = Score.builder()
            .id(nextId++)
            .name("가나디")
            .kor(55)
            .eng(66)
            .math(77)
            .total(55 + 66 + 77)
            .average((double) (55 + 66 + 77) /3)
            .build();

        Score s2 = Score.builder()
            .id(nextId++)
            .name("하치와레")
            .kor(55)
            .eng(66)
            .math(33)
            .total(55 + 66 + 33)
            .average((double) (55 + 66 + 33) /3)
            .build();

        Score s3 = Score.builder()
            .id(nextId++)
            .name("치이카와")
            .kor(77)
            .eng(77)
            .math(77)
            .total(77 + 77 + 77)
            .average((double) (77 + 77 + 77) /3)
            .build();

        scoreStore.put(s1.getId(), s1);
        scoreStore.put(s2.getId(), s2);
        scoreStore.put(s3.getId(), s3);
    }

    public void save(Score score) {
        score.setId(nextId++);
        score.setTotal(score.getKor() + score.getEng() + score.getMath());
        score.setAverage((double) score.getTotal() /3);
        scoreStore.put(score.getId(), score);
    }

    public Score findById(Long id) {
        return scoreStore.get(id);
    }

    public Map<Long, Score> findAll() {
        return scoreStore;
    }

    public Score deleteById(Long id) {
        return scoreStore.remove(id);
    }
}