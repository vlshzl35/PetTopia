package com.sh.pettopia.Hojji.user.member.service;

import com.sh.pettopia.Hojji.user.member.dto.MemberRegistRequestDto;
import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
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

    // 일반 회원 조회
    public List<MemberListResponseDto> findAll() {
        List<Member> members = memberRepository.findAll(); // 엔티티 조회
        // 엔티티를 DTO로 변환
        return members.stream()
                .map(MemberListResponseDto::fromMember)
                .collect(Collectors.toList());
    }

    // 회원 상세프로필
    public MemberListResponseDto findById(Long id) {
        return memberRepository.findById(id)
            .map(MemberListResponseDto::fromMember) // Member를 MemberListResponseDto로 변환
            .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 사용자를 찾을 수 없습니다. ID: " + id));
    }

    // 시터 회원 조회
    public List<MemberListResponseDto> findAllSitterMembers(){
        List<Member> sitters = memberRepository.findAllSitterMembers(); // 시터 회원만 조회
        // 엔티티를 DTO로 변환
        return sitters.stream()
                .map(MemberListResponseDto::fromMember)
                .collect(Collectors.toList());
    }
}
