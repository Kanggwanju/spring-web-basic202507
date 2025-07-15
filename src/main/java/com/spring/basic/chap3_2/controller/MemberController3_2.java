package com.spring.basic.chap3_2.controller;

import com.spring.basic.chap3_2.entity.Member;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v3-2/members")
public class MemberController3_2 {

    private Map<String, Member> memberStore = new HashMap<>();

    public MemberController3_2() {

        /*Member member1 = new Member.Builder()
            .account("abc1234")
            .password("123")
            .nickname("뽀로로")
            .build();*/
        Member member1 = Member.builder()
                .account("abc123")
                .password("123")
                .nickname("뽀로로")
                .build();

        Member member2 = Member.builder()
                .account("def9876")
                .password("4444")
                .nickname("핑순이")
                .build();

        memberStore.put(member1.getUid(), member1);
        memberStore.put(member2.getUid(), member2);
    }

    // 전체 조회
    @GetMapping
    public List<Member> list() {
        return new ArrayList<>(memberStore.values());
    }

    // 회원 등록
    // ?account=xxx&password=xxx&nickname=xxx   -> 불편
    // 전송할 데이터를 JSON 객체로 묶어서 보내줘 내가 풀어서 쓸게
    // @RequestBody
    /*
        {
            "account": "xxx",
            "password": "xxx",
            "nickname": "xxx"
        }
     */
    @PostMapping
    public String join(@RequestBody Member member) {
        member.setUid(UUID.randomUUID().toString());
        memberStore.put(member.getUid(), member);
        return "새로운 멤버 생성 - 닉네임: " + member.getNickname();
    }

    /*
        콘솔창 이용하여 join api 테스트
        fetch('http://localhost:9000/api/v3-2/members', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                account: 'power1234',
                password: '9944',
                nickname: '호랑이'
            })
        });

        프로그램을 이용하여 api 테스트
        Postman 다운로드
     */
}
