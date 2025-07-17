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
    private String fullName;
    private int kor, eng, math, total;
    private String average;
    private int rank;
    private int totalCount;

    // 변환 메서드, 파라미터를 여러개 받을 경우에는 이름을 of로 만들어줌.
    public static ScoreDetailResponse of(Score score, int totalCount, int ranking) {
        return ScoreDetailResponse.builder()
            .fullName(score.getName())
            .kor(score.getKor())
            .eng(score.getEng())
            .math(score.getMath())
            .total(score.getTotal())
            .totalCount(totalCount)
            .rank(ranking)
            .average(getFormattedAverageScore(score.getTotal()))
            .build();
    }
}
