package com.spring.basic.score.dto.request;

import com.spring.basic.score.entity.Score;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCreateDto {
    @NotBlank(message = "이름은 필수입니다.")
    private String studentName;

    @NotNull(message = "점수 입력은 필수입니다.")
    @Min(value = 0, message = "국어 점수는 0점 이상이여야합니다.")
    @Max(value = 100, message = "국어 점수는 100점 이하여야합니다.")
    private int korean;

    @NotNull(message = "점수 입력은 필수입니다.")
    @Min(value = 0, message = "영어 점수는 0점 이상이여야합니다.")
    @Max(value = 100, message = "영어 점수는 100점 이하여야합니다.")
    private int english;

    @NotNull(message = "점수 입력은 필수입니다.")
    @Min(value = 0, message = "수학 점수는 0점 이상이여야합니다.")
    @Max(value = 100, message = "수학 점수는 100점 이하여야합니다.")
    private int math;

    public static Score from(ScoreCreateDto dto) {
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
