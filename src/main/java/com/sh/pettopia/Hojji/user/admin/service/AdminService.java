package com.sh.pettopia.Hojji.user.admin.service;

import com.sh.pettopia.Hojji.user.admin.entity.PetsitterQualificationApplicationEntity;
import com.sh.pettopia.Hojji.user.admin.repository.AdminRepository;
import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import com.sh.pettopia.mypage.dto.PetsitterQulificationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    // Userid에 해당하는 펫시터 자격 요청 폼을 가져옵니다
    public PetsitterQulificationResponseDto findApplicationById(Long id) {
        return adminRepository.findById(id)
            .map(PetsitterQulificationResponseDto::fromEntity) // Member를 MemberListResponseDto로 변환
            .orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 사용자를 찾을 수 없습니다. ID: " + id));
    }
}
