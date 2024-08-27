package com.sh.pettopia.mypage.service;

import com.sh.pettopia.Hojji.user.admin.entity.PetsitterQualificationApplicationEntity;
import com.sh.pettopia.Hojji.user.admin.repository.AdminRepository;
import com.sh.pettopia.Hojji.user.member.entity.Member;
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
    public void profileUpdate(ProfileUpdateRequestDto dto) {
        Member member = myPageRepository.findById(dto.getId()).orElseThrow();
        System.out.println("변경전: " + member);
        member.profileUpdate(dto);
        System.out.println("변경후: " + member);

    }

    public void regist(PesitterQualificationRegistRequestDto dto) {
        PetsitterQualificationApplicationEntity entity = dto.toEntity();
        entity = adminRepository.save(entity);
    }
}