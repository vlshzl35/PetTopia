package com.sh.pettopia.Hojji.user.member.controller;

import com.sh.pettopia.Hojji.user.admin.service.AdminService;
import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.service.MemberService;
import com.sh.pettopia.mypage.dto.PetsitterQulificationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/sitterRequestForm")
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {

    private final MemberService memberService;
    private final AdminService adminService;

    @GetMapping("/userProfile/{id}")
    public String UserProfile(@PathVariable Long id, Model model) {
        // 모든 회원을 조회하는 findBy를 쓰지만 파라미터로 받아오는 id는 신청대기중인 상태의 회원이다.
        MemberListResponseDto member = memberService.findById(id);
        log.info("회원 정보를 불러옵니다");
        model.addAttribute("member", member);

        return "admin/userProfile";
    }

    @GetMapping("/sitterRequestForm/{id}")
    public String sitterRequestForm(@PathVariable Long id, Model model) {
        // 모든 회원을 조회하는 findBy를 쓰지만 파라미터로 받아오는 id는 신청대기중인 상태의 회원이다.
        MemberListResponseDto member = memberService.findById(id);
        log.info("펫시터 자격 신청서를 불러옵니다");
        model.addAttribute("member", member);

        // 시터 자격 요청폼 불러오기
        PetsitterQulificationResponseDto applicationDto = adminService.findApplicationById(id);
        model.addAttribute("applicationDto", applicationDto);

        return "admin/sitterRequestForm";
    }
}
