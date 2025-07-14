package com.spring.basic.chap2_4.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    // null과 0의차이 : null은 가격자체가 설정이 안된것, 0은 그냥 0원
    private Long id; // 책을 유일하게 구분할 수 있는 식별자
    private String title;
    private String author;
    private int price;

    
    // 수정 편의 메서드 
    public void updateBookInfo(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
