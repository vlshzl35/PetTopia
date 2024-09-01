package com.sh.pettopia.Hojji.user.member.service;

import com.sh.pettopia.Hojji.user.member.entity.Authority;

import com.sh.pettopia.Hojji.user.member.dto.MemberRegistRequestDto;
import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import com.sh.pettopia.Hojji.user.member.repository.MemberRepository;
import com.sh.pettopia.choipetsitter.repository.PetSitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final PetSitterRepository petSitterRepository;

    public void registMember(MemberRegistRequestDto dto) {
        Member member = dto.toMember();
        log.debug("member = {}", member);

        // 기본 권한을 Member로 설정합니다.
        member.setDefaultAuthorities();
        log.debug("member 권한 = {}", member.getAuthorities());

        // member를 등록합니다.
        memberRepository.save(member);
    }

    public boolean sameEmailCheck(String memberEmail) {
        return memberRepository.existsByEmail(memberEmail);
    }

    // 일반 회원 조회
//    public List<MemberListResponseDto> findAll(String q, Pageable pageable) {
//        List<Member> members = memberRepository.findAll(pageable); // 엔티티 조회
//        // 엔티티를 DTO로 변환
//        return members.stream()
//                .map(MemberListResponseDto::fromMember)
//                .collect(Collectors.toList());
//    }

    // 일반 회원 조회
    public Page<MemberListResponseDto> findAll(Authority authority, Pageable pageable) {
        Page<Member> members = authority != null?
                            memberRepository.findByAuthorityContaining(authority,pageable) :
                            memberRepository.findAll(pageable); // 엔티티 조회

        // 엔티티를 DTO로 변환
        return members.map(MemberListResponseDto::fromMember);
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

    // 시터 자격을 요청중인 회원 조회
    // 이름, 이메일, 상태, 수락/거부 상태
    public List<MemberListResponseDto> findPendingSitterMembers(){
        List<Member> pendingSitters =  memberRepository.findPendingSitterMembers(SitterStatus.PENDING);
        log.info("pendingSitters = {}", pendingSitters);

        return pendingSitters.stream()
                .map(MemberListResponseDto::fromMember)
                .collect(Collectors.toList());
    }

    // 시터자격 요청 수락/거절로 sitterStatus를 변경
    // tbl_authority에 sitter_role추가
    public void updateSitterStatus(Long memberId, SitterStatus sitterStatus) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다: " + memberId));

        member.setSitterStatus(sitterStatus);
        memberRepository.save(member);
    }

    public void grantSitterAuthority(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 사용자를 찾을 수 없습니다. ID: " + memberId));
        member.getAuthorities().add(Authority.ROLE_SITTER); // 시터 권한 추가
        memberRepository.save(member);
    }

//    public void updateSitterStatus(Long memberId, SitterStatus sitterStatus) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다: " + memberId));
//
//        member.setSitterStatus(sitterStatus);
//        memberRepository.save(member);
//    }
//
//    public void grantSitterAuthority(Long memberId) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 사용자를 찾을 수 없습니다. ID: " + memberId));
//        member.getAuthorities().add(Authority.ROLE_SITTER); // 시터 권한 추가
//        memberRepository.save(member);
//    }
//
//    public PetSitter saveMemberToEntity(MemberListResponseDto memberDto) {
//        PetSitterAddress petSitterAddress = new PetSitterAddress(null, memberDto.getAddress(), null, null);
//        PetSitter petSitter = new PetSitter();
//
//        petSitter.setPetSitterAddress(petSitterAddress);
//        petSitter.setPetSitterId(memberDto.getEmail());
//
//        PetSitter saved = petSitterRepository.save(petSitter);
//        log.info("petSitterService/saveMemberToEntity = {}", saved);
//        return saved;
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    public void processSitterUpdate(Long memberId, SitterStatus sitterStatus) {
//        updateSitterStatus(memberId, sitterStatus);
//        grantSitterAuthority(memberId);
//
//        if (sitterStatus == SitterStatus.APPROVED) {
//            MemberListResponseDto memberDto = findById(memberId);
//            saveMemberToEntity(memberDto);
//        }
//    }
}
