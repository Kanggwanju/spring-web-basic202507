package com.spring.basic.chap5_3.dto.request;

import com.spring.basic.chap3_2.entity.Member;
import lombok.*;

// 회원 가입 전용 객체
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCreateDto {

    private String userAcc;
    private String pw;
    private String nick;
    
    // DTO를 Entity로 변환하는 메서드
    // 정적 팩토리 메서드 패턴
    public static Member from(MemberCreateDto dto) {
        return Member.builder()
            .account(dto.getUserAcc())
            .password(dto.getPw())
            .nickname(dto.getNick())
            .build();
    }
}
