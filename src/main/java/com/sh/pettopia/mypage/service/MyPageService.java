package com.sh.pettopia.mypage.service;

import com.sh.pettopia.Hojji.user.member.entity.Member;
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
    public void profileUpdate(ProfileUpdateRequestDto dto) {
        Member member = myPageRepository.findById(dto.getId()).orElseThrow();
        System.out.println("변경전: " + member);
        member.profileUpdate(dto);
        System.out.println("변경후: " + member);

    }
}