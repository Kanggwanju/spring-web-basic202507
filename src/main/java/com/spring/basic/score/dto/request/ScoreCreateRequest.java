package com.spring.basic.score.dto.request;

import com.spring.basic.score.entity.Score;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCreateRequest {
    // 공백문자, null 둘다 허용하지 않음
    @NotEmpty(message = "이름은 필수값입니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글로만 작성하세요!")
    private String studentName;

    @Min(value = 0, message = "국어 점수는 0점 이상이어야합니다.")
    @Max(value = 100, message = "국어 점수는 100점 이하여야합니다.")
    @NotNull(message = "국어점수는 필수값입니다.")
    private Integer korean;

    @Min(value = 0, message = "영어 점수는 0점 이상이어야합니다.")
    @Max(value = 100, message = "영어 점수는 100점 이하여야합니다.")
    @NotNull(message = "영어점수는 필수값입니다.")
    private Integer english;

    @Min(value = 0, message = "수학 점수는 0점 이상이어야합니다.")
    @Max(value = 100, message = "수학 점수는 100점 이하여야합니다.")
    @NotNull(message = "수학점수는 필수값입니다.")
    private Integer math;

    public static Score from(ScoreCreateRequest dto) {
        return Score.builder()
            .name(dto.getStudentName())
            .kor(dto.getKorean())
            .eng(dto.getEnglish())
            .math(dto.getMath())
//            .total(dto.getEnglish() + dto.getKorean() + dto.getMath())
//            .average(getAverageScore(dto))
            .build();
    }

//    private static double getAverageScore(ScoreCreateDto dto) {
//        return (double) (dto.getEnglish() + dto.getKorean() + dto.getMath()) /3;
//    }
}
