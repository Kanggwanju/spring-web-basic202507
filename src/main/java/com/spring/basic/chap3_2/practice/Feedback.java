package com.spring.basic.chap3_2.practice;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    private long id;
    private long feedbackId;
    private String message;
    private int rating;
}
