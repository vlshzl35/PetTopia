package com.sh.pettopia.Hojji.user.member.service;

import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 전체 조회")
    void findAll() {
        List<Member> members = memberRepository.findAll();

        // Member 객체를 MemberListResponseDto로 변환
        List<MemberListResponseDto> memberListResponseDtoList = members.stream()
                .map(MemberListResponseDto::fromMember)
                .collect(Collectors.toList());

        // 변환된 DTO 출력
        for (MemberListResponseDto member : memberListResponseDtoList) {
            System.out.println(member);
        }
    }
}
