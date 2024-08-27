package com.sh.pettopia.mypage.service;

import com.sh.pettopia.Hojji.user.admin.entity.PetsitterQualificationApplicationEntity;
import com.sh.pettopia.Hojji.user.admin.repository.AdminRepository;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import com.sh.pettopia.Hojji.user.member.repository.MemberRepository;
import com.sh.pettopia.mypage.dto.PesitterQualificationRegistRequestDto;
import com.sh.pettopia.mypage.dto.ProfileUpdateRequestDto;
import com.sh.pettopia.mypage.repository.MyPageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageRepository myPageRepository;
    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;

    public void profileUpdate(ProfileUpdateRequestDto dto) {
        Member member = myPageRepository.findById(dto.getId()).orElseThrow();
        System.out.println("변경전: " + member);
        member.profileUpdate(dto);
        System.out.println("변경후: " + member);

    }


    public void regist(PesitterQualificationRegistRequestDto dto) {
        // 1. 펫시터 자격 요청폼 저장
        PetsitterQualificationApplicationEntity entity = dto.toEntity();
        entity = adminRepository.save(entity);

        // 2. Member 엔티티 조회
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원을 찾을 수 없습니다: " + dto.getId()));
        // 3. SitterStatus를 PENDING으로 변경
        member.setSitterStatus(SitterStatus.PENDING);
        // 4. 변경된 Member 엔티티 저장
        memberRepository.save(member);
    }
}