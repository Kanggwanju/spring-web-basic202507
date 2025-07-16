package com.spring.basic.chap5_4.dto.response;

import lombok.*;

// 클라이언트에게 멤버 목록을 보내줄 때 사용할 응답 DTO
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberListResponse {
    private String id;
    private String email; // account에 대응
    private String nick;
}
