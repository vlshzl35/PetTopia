package com.sh.pettopia.Hojji.user.member.controller;

import com.sh.pettopia.Hojji.user.member.dto.MemberListResponseDto;
import com.sh.pettopia.Hojji.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userProfile")
@RequiredArgsConstructor
public class UserProfileController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public String UserProfile(@PathVariable Long id, Model model) {
        MemberListResponseDto member = memberService.findById(id);
        if (member == null) {
            // 사용자를 찾을 수 없는 경우, 404 오류를 반환하거나 다른 처리 로직 추가
            return "error/404";
        }
        model.addAttribute("member", member);
        return "admin/userProfile";
    }
}
