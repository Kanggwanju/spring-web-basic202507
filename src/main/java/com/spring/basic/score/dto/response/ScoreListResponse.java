package com.spring.basic.score.dto.response;

import com.spring.basic.score.entity.Score;
import lombok.*;

// 성적 정보 목록을 클라이언트에게 JSON으로 전송할 때 필요한 데이터만
// 컴팩트하게 담아놓은 클래스
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreListResponse {
    private Long id;
    private String maskingName;
    private int sum;
    private String avg;
    private int rank;

    // 자기 자신을 엔터티로 포장하여 변환하는 유틸 정적 메서드
    public static ScoreListResponse from(Score score) {
        return ScoreListResponse.builder()
            .id(score.getId())
            .maskingName(maskNickName(score.getName()))
            .sum(score.getTotal())
            .avg(getFormattedAverageScore(score.getTotal()))
            .build();
    }

    public static String getFormattedAverageScore(int total) {
        double avg = (double) total/3;
        return String.format("%.2f", avg);
    }

    private static String maskNickName(String originName) {

        // 이름이 2글자인 사람 처리
        if (originName.length() <= 2) {
            return originName.charAt(0) + "*";
        }

        // 나머지 경우
        // 첫글자
        char firstLetter = originName.charAt(0);
        // 마지막 글자
        char lastLetter = originName.charAt(originName.length() - 1);

        // 완성된 마스킹 네임
        String maskingName = String.valueOf(firstLetter);

        for (int i = 1; i < originName.length() - 1; i++) {
            maskingName += "*";
        }
        maskingName += lastLetter;

        return maskingName;
    }
}
