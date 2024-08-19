package com.sh.pettopia.Hojji.user.member.service;

import com.sh.pettopia.Hojji.user.member.dto.MemberRegistRequestDto;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public void registMember(MemberRegistRequestDto dto) {
        Member member = dto.toMember();
        log.debug("member = {}", member);

        // 기본 권한을 Member로 설정합니다.
        member.setDefaultAuthorities();
        log.debug("member 권한 = {}", member.getAuthorities());

        // member를 등록합니다.
        memberRepository.save(member);
    }

    @Transactional
    public boolean sameEmailCheck(String memberEmail) {
        return memberRepository.existsByEmail(memberEmail);
    }
}
