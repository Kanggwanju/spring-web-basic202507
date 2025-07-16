package com.spring.basic.score.dto.response;

import com.spring.basic.score.entity.Score;
import lombok.*;

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

    public static ScoreListResponse from(Score score) {
        return ScoreListResponse.builder()
            .id(score.getId())
            .maskingName(maskNickName(score.getName()))
            .sum(score.getTotal())
            .avg(getFormattedAverageScore(score.getTotal()))
            .build();
    }

    private static String getFormattedAverageScore(int total) {
        double avg = (double) total/3;
        return String.format("%.2f", avg);
    }

    private static String maskNickName(String originNick) {
        String str = "";
        for (int i = 0; i < originNick.length(); i++) {
            if (i == 0 || i == originNick.length()-1) {
                str += originNick.charAt(i);
            } else {
                str += "*";
            }
            
        }

        return str;
    }
}
