package com.sh.pettopia.Hojji.user.member.service;

import com.sh.pettopia.Hojji.user.member.dto.MemberRegistRequestDto;
import com.sh.pettopia.Hojji.user.member.entity.Member;

import com.sh.pettopia.Hojji.user.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public void registMember(MemberRegistRequestDto dto) {
        Member member = dto.toMember(dto);

        // 기본 권한을 MEMBER로 설정합니다.
        member.setDefaultAuthorities();
        memberRepository.save(member);
    }
}
