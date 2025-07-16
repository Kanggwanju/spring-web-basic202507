package com.spring.basic.score.entity;

import lombok.*;

// 학생 한명의 성적정보를 저장
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {
    private Long id; // 학번
    private String name; // 이름;
    private int kor, eng, math; // 국영수 점수
    private int total; // 총점
    private double average; // 평균
}
