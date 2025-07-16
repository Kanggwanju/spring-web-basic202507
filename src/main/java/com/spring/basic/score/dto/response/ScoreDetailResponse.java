package com.spring.basic.score.dto.response;

import com.spring.basic.score.entity.Score;
import lombok.*;

import static com.spring.basic.score.dto.response.ScoreListResponse.getFormattedAverageScore;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDetailResponse {
    private int kor, eng, math, total;
    private String average;
    private int rank;
    private int totalCount;

    public static ScoreDetailResponse from(Score score) {
        return ScoreDetailResponse.builder()
            .kor(score.getKor())
            .eng(score.getEng())
            .math(score.getMath())
            .total(score.getTotal())
            .average(getFormattedAverageScore(score.getTotal()))
            .build();
    }
}
